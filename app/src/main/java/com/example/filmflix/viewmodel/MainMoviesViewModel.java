package com.example.filmflix.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmflix.MoviesRepository;
import com.example.filmflix.models.Movie;

import java.util.List;

import javax.inject.Inject;

public class MainMoviesViewModel extends ViewModel {
    LiveData<List<Movie>> movies;
    MoviesRepository moviesRepository;
    @Inject
    public MainMoviesViewModel(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }
    public LiveData<List<Movie>> getMovies(Context context){
        return moviesRepository.getMovies(context);
    }
}
