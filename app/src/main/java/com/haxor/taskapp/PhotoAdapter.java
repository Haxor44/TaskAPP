package com.haxor.taskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private  List<Photo> photos;
    private Context context;

    public PhotoAdapter(List<Photo> photos1, Context contexts){
        photos = photos1;
        context = contexts;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {

        Photo photo = photos.get(position);

        Picasso.get().load(photo.getUrls().getRegular()).resize(500,500).centerCrop().into(holder.image);



    }


    public void addphoto(List<Photo> photos2){
        photos.addAll(photos2);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {

        public final ImageView image;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }


    }


}
