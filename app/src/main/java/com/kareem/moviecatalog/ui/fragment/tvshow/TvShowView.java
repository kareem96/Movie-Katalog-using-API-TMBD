package com.kareem.moviecatalog.ui.fragment.tvshow;

import com.kareem.moviecatalog.model.TvShow;

import java.util.ArrayList;

public interface TvShowView {
    void showLoading ();
    void hideLoading ();
    void showList (ArrayList<TvShow> list);
    void showNotList (String message);
    void showFailure (String message);
}
