package com.kareem.moviecatalog.ui.fragment.movie;

import com.kareem.moviecatalog.model.Movie;

import java.util.ArrayList;

public interface MovieView {

    void showLoading ();
    void hideLoading ();
    void showList (ArrayList<Movie> list);
    void showNotList (String message);
    void showFailure (String message);

}
