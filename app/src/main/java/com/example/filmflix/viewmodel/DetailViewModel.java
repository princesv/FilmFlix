package com.example.filmflix.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmflix.DetailActivity;
import com.example.filmflix.models.Movie;
import com.example.filmflix.repository.MoviesRepository;

import javax.inject.Inject;

public class DetailViewModel extends ViewModel {
    MoviesRepository moviesRepository;
    @Inject
    public DetailViewModel(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }
    public MutableLiveData<Movie> getMovieDetail(){
        return moviesRepository.movie;
    }
    public void switchFavouriteStatus(Movie movie,boolean f){
        moviesRepository.switchFavouriteStatus(movie,f);
    }
    public void updateMovie(Movie movie){
        moviesRepository.movie.setValue(movie);
    }
}
