package com.example.filmflix.di;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.filmflix.MoviesRepository;
import com.example.filmflix.di.annotations.ActivityScoped;
import com.example.filmflix.viewmodel.MainMoviesViewModel;
import com.example.filmflix.viewmodel.MainViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    ViewModelStoreOwner context;
    public ViewModelModule(ViewModelStoreOwner context){
        this.context=context;
    }
    @ActivityScoped
    @Provides
    MainMoviesViewModel getMainMovieViewModel(MainViewModelFactory mainViewModelFactory){
        return new ViewModelProvider(context, mainViewModelFactory).get(MainMoviesViewModel.class);
    }
    @ActivityScoped
    @Provides
    MainViewModelFactory getMainViewModelFactory(MoviesRepository moviesRepository){
        return new MainViewModelFactory(moviesRepository);
    }
}
