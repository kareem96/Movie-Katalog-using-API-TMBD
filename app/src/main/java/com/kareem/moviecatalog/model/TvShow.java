package com.kareem.moviecatalog.model;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static com.kareem.moviecatalog.db.DatabaseContract.getColumnInt;
import static com.kareem.moviecatalog.db.DatabaseContract.getColumnString;

import static com.kareem.moviecatalog.ui.DetailsTvShow.BACKDROP_PATH;
import static com.kareem.moviecatalog.ui.DetailsTvShow.FIRST_DATE;
import static com.kareem.moviecatalog.ui.DetailsTvShow.NAME;
import static com.kareem.moviecatalog.ui.DetailsTvShow.OVERVIEW;
import static com.kareem.moviecatalog.ui.DetailsTvShow.POPULARITY;
import static com.kareem.moviecatalog.ui.DetailsTvShow.POSTER_PATH;
import static com.kareem.moviecatalog.ui.DetailsTvShow.VOTE_AVERAGE;
import static com.kareem.moviecatalog.ui.DetailsTvShow.VOTE_COUNT;
import static com.kareem.moviecatalog.ui.DetailsTvShow._ID;

public class TvShow implements Parcelable {
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("id")
    private Integer id;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("genre_ids")
    private String[] genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String firstDate;


    protected TvShow(Parcel in) {
        voteCount = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        voteAverage = in.readString();
        name = in.readString();
        popularity = in.readString();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalName = in.readString();
        genreIds = in.createStringArray();
        backdropPath = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        firstDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        if (voteCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(voteCount);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        if (voteAverage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(voteAverage);
        }
        parcel.writeString(name);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(popularity);
        }
        parcel.writeString(posterPath);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalName);
        parcel.writeStringArray(genreIds);
        parcel.writeString(backdropPath);
        parcel.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        parcel.writeString(overview);
        parcel.writeString(firstDate);
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    public TvShow(Cursor cursor) {
        this.voteCount = getColumnString(cursor, VOTE_COUNT);
        this.id = getColumnInt(cursor, _ID);
        this.voteAverage = getColumnString(cursor, VOTE_AVERAGE);
        this.name = getColumnString(cursor, NAME);
        this.popularity = getColumnString(cursor, POPULARITY);
        this.posterPath = getColumnString(cursor, POSTER_PATH);
        this.backdropPath = getColumnString(cursor, BACKDROP_PATH);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.firstDate = getColumnString(cursor, FIRST_DATE);


    }
}