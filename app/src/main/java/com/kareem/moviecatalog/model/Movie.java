package com.kareem.moviecatalog.model;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.kareem.moviecatalog.db.DatabaseContract.getColumnInt;
import static com.kareem.moviecatalog.db.DatabaseContract.getColumnString;
import static com.kareem.moviecatalog.ui.DetailsMovie.BACKDROP_PATH;
import static com.kareem.moviecatalog.ui.DetailsMovie.OVERVIEW;
import static com.kareem.moviecatalog.ui.DetailsMovie.POPULARITY;
import static com.kareem.moviecatalog.ui.DetailsMovie.POSTER_PATH;
import static com.kareem.moviecatalog.ui.DetailsMovie.RELEASE_DATE;
import static com.kareem.moviecatalog.ui.DetailsMovie.TITLE;
import static com.kareem.moviecatalog.ui.DetailsMovie.VOTE_AVERAGE;
import static com.kareem.moviecatalog.ui.DetailsMovie.VOTE_COUNT;
import static com.kareem.moviecatalog.ui.DetailsMovie._ID;

public class Movie implements Parcelable {
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("id")
    private Integer id;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("originalTitle")
    private String originalTitle;
    @SerializedName("genre_ids")
    private String[] genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        if (voteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(voteCount);
        }
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(voteAverage);
        }
        dest.writeString(title);
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(popularity);
        }
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeByte((byte)(adult == null ? 0 : adult ? 1 : 2));
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    public Movie(Cursor cursor) {
        this.voteCount = getColumnString(cursor, VOTE_COUNT);
        this.id = getColumnInt(cursor, _ID);
        this.voteAverage = getColumnString(cursor, VOTE_AVERAGE);
        this.title = getColumnString(cursor, TITLE);
        this.popularity = getColumnString(cursor, POPULARITY);
        this.posterPath = getColumnString(cursor, POSTER_PATH);
        this.backdropPath = getColumnString(cursor, BACKDROP_PATH);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.releaseDate = getColumnString(cursor, RELEASE_DATE);
    }

    protected Movie(Parcel in) {
        voteCount = in.readString();
        if (in.readByte() == 0){
            id = null;
        }else {
            id = in.readInt();
        }
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null :tmpVideo == 1;
        voteAverage = in.readString();
        title = in.readString();
        popularity = in.readString();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        genreIds = in.createStringArray();
        backdropPath = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}