package com.example.newsapp2.rests;

import com.example.newsapp2.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("top-headlines")
    Call< ResponseModel> getLatestNews(
            @Query("sources") String source,
            @Query("apiKey") String apiKey);


}
