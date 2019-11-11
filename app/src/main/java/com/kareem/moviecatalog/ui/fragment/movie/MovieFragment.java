package com.kareem.moviecatalog.ui.fragment.movie;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kareem.moviecatalog.R;
import com.kareem.moviecatalog.adapter.MovieAdapter;
import com.kareem.moviecatalog.model.Movie;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieView {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MoviePresenter moviePresenter;
    private ArrayList<Movie> listMovie;
    private MovieAdapter movieAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null){
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    loadMovie();
                }
            });

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadMovie();
                }
            });
        }
        if (savedInstanceState != null && savedInstanceState.containsKey("data_result")){
            listMovie = savedInstanceState.getParcelableArrayList("data_result");
            loadMovie();
        }else if (savedInstanceState != null && savedInstanceState.containsKey("data_recycler_view")){
            Parcelable parcelable = savedInstanceState.getParcelable("data_recycler_view");
            swipeRefreshLayout.setRefreshing(false);
            recyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (listMovie != null){
            outState.putParcelableArrayList("data_result", listMovie);
        }
        if (listMovie != null){
            outState.putParcelable("data_recycler_view", recyclerView.getLayoutManager().onSaveInstanceState());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout =  view.findViewById(R.id.swipeRefresh);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        listMovie = new ArrayList<>();
        moviePresenter = new MoviePresenter(this);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showList(ArrayList<Movie> list) {
        listMovie.clear();
        listMovie.addAll(list);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNotList(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    private void loadMovie() {
        moviePresenter.getListMovie();
        movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setListMovie(listMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(movieAdapter);
    }


}