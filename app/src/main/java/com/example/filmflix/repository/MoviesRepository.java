package com.example.filmflix.repository;

import android.content.Context;
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
    public MutableLiveData<Movie> movie;
    @Inject
    public MoviesRepository(MoviewApi movieApi, MoviesDB moviesDB){
        this.movieApi=movieApi;
        this.moviesDB=moviesDB;
        movie=new MutableLiveData<>();
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
    public void switchFavouriteStatus(int movieId,boolean f){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            moviesDB.moviesDao().updateMovieFavoriteStatus(movieId,f);
        });
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
                        for(int i=0;i<movieList.getResults().size();i+=1){
                            Log.d("Movie>>>",movieList.getResults().get(i).toString());
                        }
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
