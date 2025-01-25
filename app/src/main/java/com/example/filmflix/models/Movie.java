package com.example.filmflix.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Movie {
    private boolean adult;
    private String backdrop_path;
   // private List<Integer> genreIds;
    @PrimaryKey
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    //favourite flag
    private boolean isFavourite=false;

    // Getters and Setters
    public Movie(){

    }
    public Movie(Movie movie,boolean isFavourite){
        this.adult=movie.adult;
        this.isFavourite=movie.isFavourite;
        this.id=movie.id;
        this.backdrop_path=movie.backdrop_path;
        this.original_language=movie.original_language;
        this.original_title=movie.original_title;
        this.adult=movie.adult;
        this.overview=movie.overview;
        this.popularity=movie.popularity;
        this.poster_path=movie.poster_path;
        this.release_date=movie.release_date;
        this.title=movie.title;
        this.video=movie.video;
        this.vote_average=movie.vote_average;
        this.vote_count=movie.vote_count;
        this.isFavourite=isFavourite;
    }
    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
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

    /*public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String originalTitle) {
        this.original_title = originalTitle;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String releaseDate) {
        this.release_date = releaseDate;
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

    public void setVote_average(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int voteCount) {
        this.vote_count = voteCount;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "adult=" + adult +
                ", backdropPath='" + backdrop_path + '\'' +
                ", id=" + id +
                ", originalLanguage='" + original_language + '\'' +
                ", originalTitle='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + poster_path + '\'' +
                ", releaseDate='" + release_date + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + vote_average +
                ", voteCount=" + vote_count +
                '}';
    }
}

