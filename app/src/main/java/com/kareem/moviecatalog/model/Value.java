package com.kareem.moviecatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Value {
    @SerializedName("result")
    private ArrayList<Movie> result;

    public ArrayList<TvShow> getResult() {
        return result;
    }
}
