package com.kareem.moviecatalog.api;

import com.kareem.moviecatalog.model.Value;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("discover/movie")
    Call<Value> getMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language);


    @GET("discover/tv")
    Call<Value> getTvShow(
            @Query("api_key") String apiKey,
            @Query("language") String language);

}