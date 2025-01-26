package com.example.filmflix.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmflix.models.MovieList;
import com.example.filmflix.repository.MoviesRepository;
import com.example.filmflix.models.Movie;

import java.util.List;

import javax.inject.Inject;

public class MainMoviesViewModel extends ViewModel {
    MoviesRepository moviesRepository;
    public MutableLiveData<Integer> isFav;
    @Inject
    public MainMoviesViewModel(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
        isFav=new MutableLiveData<>();
        isFav.setValue(0);
    }
    public LiveData<List<Movie>> getMovies(Context context){
        return moviesRepository.getMovies(context);
    }
    public LiveData<List<Movie>> getFavouriteMovies(){
        return moviesRepository.getFavouriteMovies();
    }
    public void updateDetailMovie(Movie movie){
        moviesRepository.movie.setValue(movie);
    }
}
