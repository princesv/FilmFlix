package com.example.filmflix.di;

import com.example.filmflix.MoviesRepository;
import com.example.filmflix.di.annotations.ApplicationScoped;
import com.example.filmflix.retrofit.MoviewApi;
import com.example.filmflix.room.MoviesDB;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @ApplicationScoped
    @Provides
    MoviesRepository getMoviesRepo(MoviewApi moviewApi, MoviesDB moviesDB){
        return new MoviesRepository(moviewApi,moviesDB);
    }
}
