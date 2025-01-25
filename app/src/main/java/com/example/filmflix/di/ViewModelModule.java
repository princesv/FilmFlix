package com.example.filmflix.di;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.filmflix.repository.MoviesRepository;
import com.example.filmflix.di.annotations.ActivityScoped;
import com.example.filmflix.viewmodel.DetailViewModel;
import com.example.filmflix.viewmodel.DetailViewModelFactory;
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

    @ActivityScoped
    @Provides
    DetailViewModelFactory getDetailViewModelFactory(MoviesRepository moviesRepository){
        return new DetailViewModelFactory(moviesRepository);
    }

    @ActivityScoped
    @Provides
    DetailViewModel getDetailViewModel(DetailViewModelFactory detailViewModelFactory){
        return new ViewModelProvider(context,detailViewModelFactory).get(DetailViewModel.class);
    }
}
