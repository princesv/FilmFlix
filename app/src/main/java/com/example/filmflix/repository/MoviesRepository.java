package com.example.filmflix.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.filmflix.FilmFlixApplication;
import com.example.filmflix.SearchActivity;
import com.example.filmflix.models.Movie;
import com.example.filmflix.models.MovieList;
import com.example.filmflix.retrofit.MoviewApi;
import com.example.filmflix.room.MoviesDB;
import com.example.filmflix.utils.NetworkUtils;
import com.example.filmflix.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    MoviewApi movieApi;
    MoviesDB moviesDB;
    public MutableLiveData<Movie> movie;
    public MutableLiveData<List<Movie>> searchResult;
    @Inject
    public MoviesRepository(MoviewApi movieApi, MoviesDB moviesDB){
        this.movieApi=movieApi;
        this.moviesDB=moviesDB;
        movie=new MutableLiveData<>();
        searchResult=new MutableLiveData<>();
    }
    public LiveData<List<Movie>> getMovies(Context context){
        if(NetworkUtils.isInternetAvailable(context)){
            getMoviesFromNetwork();
        }
        return getMoviesFromDb();
    }
    public LiveData<List<Movie>> getMoviesFromDb(){
        return moviesDB.moviesDao().getMovies();
    }

    public LiveData<List<Movie>> getFavouriteMovies(){
        return moviesDB.moviesDao().getFavouriteMovies();
    }
    public void switchFavouriteStatus(Movie movie,boolean f){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            moviesDB.moviesDao().putMovie(movie);
            moviesDB.moviesDao().updateMovieFavoriteStatus(movie.getId(),f);
        });
    }
    public void searchMovie(String movieName){
        Call<MovieList> call = movieApi.searchMovies(
                movieName,
                false,
                "en-US",
                1,
                Utils.AUTH_TOKEN
        );
        List<Movie> searchedMovies;

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    searchResult.setValue(response.body().getResults());

                } else {
                    Log.d("MoviesRepository",response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("MovieRepository","Network call failed");
            }
        });
    }
    void getMoviesFromNetwork(){
        Call<MovieList> call = movieApi.getMovies(Utils.apiKey);

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    MovieList movieList = response.body();
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(() -> {
                        moviesDB.moviesDao().putMovies(movieList.getResults());
                        // Process the movies as needed
                    });
                } else {
                    // Handle the error, response code is available through response.code()
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("MovieRepository","Network call failed");
            }
        });

    }
    }
