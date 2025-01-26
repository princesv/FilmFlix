package com.example.filmflix.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmflix.SearchActivity;
import com.example.filmflix.models.Movie;
import com.example.filmflix.repository.MoviesRepository;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {
    public LiveData<String> searchText;
    public MutableLiveData<List<Movie>> searchResult;
    MoviesRepository moviesRepository;
    @Inject
    public SearchViewModel(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
        searchResult=moviesRepository.searchResult;
    }
    public void searchMovies(String movieName){
        moviesRepository.searchMovie(movieName);
    }
    public void updateDetailMovie(Movie movie){
        moviesRepository.movie.setValue(movie);
    }
}
