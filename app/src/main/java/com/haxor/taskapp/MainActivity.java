package com.haxor.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    private int page = 1;

    private Retrofit retrofit = null;
    private UnsplashApi splash;
    private ImageView img;
    private RecyclerView recyc;
    PhotoAdapter adapter;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HInterceptor("76d9599b289c33d1fe08550bee781489f553d436ba7a102c8f03ff382b11026e")).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.unsplash.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            splash = retrofit.create(UnsplashApi.class);
        }

        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        recyc = findViewById(R.id.recycleview);
        progress = findViewById(R.id.pro);


        GridLayoutManager  layoutmanager = new GridLayoutManager(this, 2);
        //LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        recyc.setLayoutManager(layoutmanager);

        adapter = new PhotoAdapter(new ArrayList<Photo>(), this);
        recyc.setAdapter(adapter);

        recyc.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutmanager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                loadImage();
            }
        });

        loadImage();
        bottomnav.setOnNavigationItemReselectedListener(navs);

    }

    private BottomNavigationView.OnNavigationItemReselectedListener navs = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {

                case R.id.my_profile:
                    fragment = new ProfileFragment();
                    break;



                case R.id.logout:
                    fragment = new Logout();
                    break;


            }


            getSupportFragmentManager().beginTransaction().replace(R.id.frames, fragment).commit();

        }
    };

    private void loadImage() {

        progress.setVisibility(View.VISIBLE);


        //progress.setVisibility(View.VISIBLE);

        img = findViewById(R.id.imageView);


        Call<List<Photo>> call = splash.getPhoto(page,10, "latest");

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                List<Photo> photos3 = response.body();
                page++;
                adapter.addphoto(photos3);
                recyc.setAdapter(adapter);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                progress.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });






    }
}
