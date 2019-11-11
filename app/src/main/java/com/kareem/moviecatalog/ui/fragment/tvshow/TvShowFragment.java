package com.kareem.moviecatalog.ui.fragment.tvshow;

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
import com.kareem.moviecatalog.R;
import com.kareem.moviecatalog.adapter.TvShowAdapter;
import com.kareem.moviecatalog.model.TvShow;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TvShowPresenter tvShowPresenter;
    private ArrayList<TvShow> listTvShow;
    private TvShowAdapter tvShowAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
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
            listTvShow = savedInstanceState.getParcelableArrayList("data_result");
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
        if (listTvShow != null){
            outState.putParcelableArrayList("data_result", listTvShow);
        }
        if (listTvShow != null){
            outState.putParcelable("data_recycler_view", recyclerView.getLayoutManager().onSaveInstanceState());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout =  view.findViewById(R.id.swipeRefresh);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        listTvShow = new ArrayList<>();
        tvShowPresenter = new TvShowPresenter(this);
    }




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showList(ArrayList<TvShow> list) {

    }

    @Override
    public void showNotList(String message) {

    }

    @Override
    public void showFailure(String message) {

    }

    private void loadMovie() {

        tvShowPresenter.getListTvMovie();
        tvShowAdapter = new TvShowAdapter(getContext());
        tvShowAdapter.setListMovie(listTvShow);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(tvShowAdapter);
    }
}