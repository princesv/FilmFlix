package com.example.filmflix.di;

import android.app.Activity;

import com.example.filmflix.MoviesRepository;
import com.example.filmflix.di.annotations.ApplicationScoped;
import com.example.filmflix.viewmodel.MainMoviesViewModel;

import javax.inject.Singleton;

import dagger.Component;

@ApplicationScoped
@Component(modules = {RetrofitModule.class,RepositoryModule.class,DbModule.class})
public interface ApplicationComponent {
    void injectDependencies(Activity activity);
    MoviesRepository getRepo();
}
