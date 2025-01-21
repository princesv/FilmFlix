package com.example.filmflix;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.filmflix.models.Movie;
import com.example.filmflix.models.MovieList;
import com.example.filmflix.retrofit.MoviewApi;
import com.example.filmflix.room.MoviesDB;
import com.example.filmflix.utils.NetworkUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {
    MoviewApi movieApi;
    MoviesDB moviesDB;
    @Inject
    public MoviesRepository(MoviewApi movieApi, MoviesDB moviesDB){
        this.movieApi=movieApi;
        this.moviesDB=moviesDB;
    }
    public LiveData<List<Movie>> getMovies(Context context){
        if(NetworkUtils.isInternetAvailable(context)){
            getMoviesFromNetwork();
        }
        return moviesDB.moviesDao().getMovies();
    }
    void getMoviesFromNetwork(){
        Call<MovieList> call = movieApi.getMovies();

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
