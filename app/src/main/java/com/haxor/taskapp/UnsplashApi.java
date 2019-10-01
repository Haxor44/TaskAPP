package com.haxor.taskapp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UnsplashApi {

    @GET("photos")
    Call<List<Photo>> getPhoto(@Query("page") int page, @Query("per_page") int pages, @Query("order_by") String orderBy);
}
