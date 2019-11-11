package com.kareem.moviecatalog.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kareem.moviecatalog.BuildConfig;
import com.kareem.moviecatalog.R;
import com.kareem.moviecatalog.db.MovieHelper;
import com.kareem.moviecatalog.model.TvShow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import butterknife.ButterKnife;

public class DetailsTvShow extends AppCompatActivity {

    public static final String EXTRAMOVIE = "extra_movie";

    TextView tvVoteCount, tvVoteAverage, tvPopularity, tvOverview, tvTitle, tvReleaseDate;
    ImageView ivBackdropPath, ivPosterPath;

    public static String _ID = "_id";
    public static String NAME = "name";
    public static String VOTE_COUNT = "vote_count";
    public static String VOTE_AVERAGE = "vote_average";
    public static String POPULARITY = "popularity";
    public static String OVERVIEW = "overview";
    public static String BACKDROP_PATH = "backdrop_path";
    public static String POSTER_PATH = "poster_path";
    public static String FIRST_DATE = "air_first_date";

//
//    @BindView(R.id.tv_overview)
//    TextView tvOverviewTvShow;
//    @BindView(R.id.tv_rating)
//    TextView tvRatingTvShow;
//    @BindView(R.id.tv_title)
//    TextView tvTitleTvShow;
//    @BindView(R.id.tv_release_date)
//    TextView tvReleaseDateTvShow;
//    @BindView(R.id.iv_backdrop_path)
//    ImageView ivBackdropPathTvShow;
//    @BindView(R.id.iv_poster_path)
//    ImageView ivPosterPathTvShow;
//    @BindView(R.id.toolbar2)
//    Toolbar toolbar;


    private String name, voteCount, voteAverage, popularity, overview, backdropPath, postePath, firstDate;
    private MovieHelper movieHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_tv_show);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.TOP);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        tvVoteCount = findViewById(R.id.tv_rating);
        tvOverview = findViewById(R.id.tv_overview);
        ivBackdropPath = findViewById(R.id.iv_backdrop_path);
        ivPosterPath = findViewById(R.id.iv_poster_path);
        tvTitle = findViewById(R.id.tv_title);
        tvReleaseDate = findViewById(R.id.tv_release_date);

        Uri uri = getIntent().getData();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null
                    , null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    TvShow tvShow = new TvShow(cursor);
                }
                cursor.close();
            }
        }

            Intent intent = getIntent();
            if (intent != null) {
                name = intent.getStringExtra(NAME);
                voteCount = intent.getStringExtra(VOTE_COUNT);
                voteAverage = intent.getStringExtra(VOTE_AVERAGE);
                popularity = intent.getStringExtra(POPULARITY);
                overview = intent.getStringExtra(OVERVIEW);
                backdropPath = intent.getStringExtra(BACKDROP_PATH);
                postePath = intent.getStringExtra(POSTER_PATH);
                firstDate = intent.getStringExtra(FIRST_DATE);
            }

            setTitle(name);
            tvPopularity.setText(popularity);
            tvOverview.setText(overview);
            Glide.with(getBaseContext())
                    .load(BuildConfig.IMG_URL + "" + backdropPath)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(ivBackdropPath);
            Glide.with(getBaseContext())
                    .load(BuildConfig.IMG_URL + "" + postePath)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(ivPosterPath);
            tvTitle.setText(name);

            String dateInput = firstDate;
            SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat simpleDateFormatOutput = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
            Date date;
            String dateOuput = null;
            try {
                date = simpleDateFormatInput.parse(dateInput);
                dateOuput = simpleDateFormatOutput.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvReleaseDate.setText(dateOuput);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (movieHelper != null){
            movieHelper.close();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(DetailsTvShow.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}