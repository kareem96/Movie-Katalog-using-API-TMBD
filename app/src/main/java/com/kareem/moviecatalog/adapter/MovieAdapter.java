package com.kareem.moviecatalog.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kareem.moviecatalog.BuildConfig;
import com.kareem.moviecatalog.ui.DetailsMovie;
import com.kareem.moviecatalog.R;
import com.kareem.moviecatalog.model.Movie;
import com.kareem.moviecatalog.ui.DetailsTvShow;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> listMovie;
    private Context context;


    public MovieAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final  Movie movie = getListMovie().get(position);
        Glide.with(context)
                .load(BuildConfig.IMG_URL +""+ movie.getPosterPath())
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).centerCrop())
                .into(holder.ivPosterPath);
        holder.carList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsMovie.class);
                intent.putExtra(DetailsMovie.EXTRAMOVIE, listMovie.get(position));
                intent.putExtra(DetailsTvShow.EXTRAMOVIE, listMovie.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPosterPath;
        private CardView carList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPosterPath = itemView.findViewById(R.id.iv_poster_path);
            carList = itemView.findViewById(R.id.card_list);
        }
    }
}
