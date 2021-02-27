package com.example.thomasngo_mobiledev_project1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {

    @GET("accounts")
    Call<List<Post>> getPosts();
}
