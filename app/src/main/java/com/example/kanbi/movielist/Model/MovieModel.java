package com.example.kanbi.movielist.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
//import static com.example.kanbi.movielist.Model.MovieModel.TABLE_NAME;

/**
 * Created by kanbi on 14/12/2017.
 */
//@Entity(tableName = TABLE_NAME)
public class MovieModel {


    @SerializedName("adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids=new ArrayList<>();

    @SerializedName("id")
    private Integer id;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("title")
    private String title;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("vote_count")
    private String vote_count;

    public MovieModel(boolean adult, String backdrop_path, List<Integer> genre_ids, Integer id, String original_language, String original_title, String overview, double popularity, String posterPath, String release_date, String title, boolean video, double vote_average, String vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }
}


//codes below are saved for experimenting Room persistent

/*public static final String TABLE_NAME = "Movies";

    @PrimaryKey(autoGenerate=true)
    private int movieId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @ColumnInfo(name="adult")
    @SerializedName("adult")
    private boolean adult;

    @ColumnInfo(name="backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop_path;

    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genre_ids=new ArrayList<>();

    @ColumnInfo(name="id")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo(name="original_language")
    @SerializedName("original_language")
    private String original_language;

    @ColumnInfo(name="original_title")
    @SerializedName("original_title")
    private String original_title;

    @ColumnInfo(name="overview")
    @SerializedName("overview")
    private String overview;


    @ColumnInfo(name="popularity")
    @SerializedName("popularity")
    private double popularity;

    @ColumnInfo(name="posterPath")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name="release_date")
    @SerializedName("release_date")
    private String release_date;

    @ColumnInfo(name="title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name="video")
    @SerializedName("video")
    private boolean video;

    @ColumnInfo(name="vote_average")
    @SerializedName("vote_average")
    private double vote_average;

    @ColumnInfo(name="vote_count")
    @SerializedName("vote_count")
    private String vote_count;*/
