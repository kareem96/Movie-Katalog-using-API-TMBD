package com.kareem.moviecatalog.ui.fragment.movie;

import com.kareem.moviecatalog.BuildConfig;
import com.kareem.moviecatalog.api.ApiConfig;
import com.kareem.moviecatalog.api.ApiInterface;
import com.kareem.moviecatalog.model.Value;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    private  MovieView movieView;

    public MoviePresenter(MovieView movieView){
        this.movieView = movieView;
    }

    public void getListMovie(){
        movieView.showLoading();

        ApiInterface api = ApiConfig.getNetwork().create(ApiInterface.class);
        Call<Value> call = api.getMovie(BuildConfig.API_KEY, Locale.getDefault().toString());
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                movieView.hideLoading();
                if (response.isSuccessful()){
                    movieView.showList(response.body().getResult());
                }else {
                    movieView.showNotList(response.message());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                movieView.hideLoading();
                movieView.showFailure(t.getMessage());
            }
        });
    }
}
