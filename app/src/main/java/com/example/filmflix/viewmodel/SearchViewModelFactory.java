package com.example.filmflix.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.filmflix.repository.MoviesRepository;

import javax.inject.Inject;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    MoviesRepository moviesRepository;
    @Inject
    public SearchViewModelFactory(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SearchViewModel(moviesRepository);
    }
}
