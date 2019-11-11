package com.kareem.moviecatalog.ui.fragment.tvshow;

import com.kareem.moviecatalog.BuildConfig;
import com.kareem.moviecatalog.api.ApiConfig;
import com.kareem.moviecatalog.api.ApiInterface;
import com.kareem.moviecatalog.model.Value;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowPresenter {
    private  TvShowView tvShowView;

    public TvShowPresenter(TvShowView tvShowView){
        this.tvShowView =  tvShowView;
    }

    public void getListTvMovie(){
        tvShowView.showLoading();

        ApiInterface api = ApiConfig.getNetwork().create(ApiInterface.class);
        Call<Value> call = api.getTvShow(BuildConfig.API_KEY, Locale.getDefault().toString());
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                tvShowView.hideLoading();
                if (response.isSuccessful()){
                    tvShowView.showList(response.body().getResult());
                }else {
                    tvShowView.showNotList(response.message());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                tvShowView.hideLoading();
                tvShowView.showFailure(t.getMessage());
            }
        });
    }
}
