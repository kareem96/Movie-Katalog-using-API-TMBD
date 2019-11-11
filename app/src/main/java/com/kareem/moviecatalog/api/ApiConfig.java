package com.kareem.moviecatalog.api;

import com.kareem.moviecatalog.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static Retrofit getNetwork(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
