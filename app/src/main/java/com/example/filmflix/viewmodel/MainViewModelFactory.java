package com.example.filmflix.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.filmflix.MoviesRepository;
import com.example.filmflix.di.annotations.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class MainViewModelFactory implements ViewModelProvider.Factory {
    MoviesRepository moviesRepository;
    @Inject
    public MainViewModelFactory(MoviesRepository moviesRepository){
        this.moviesRepository=moviesRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainMoviesViewModel(moviesRepository) ;
    }
}
