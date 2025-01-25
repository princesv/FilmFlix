package com.example.filmflix.di;

import android.app.Activity;

import com.example.filmflix.repository.MoviesRepository;
import com.example.filmflix.di.annotations.ApplicationScoped;

import dagger.Component;

@ApplicationScoped
@Component(modules = {RetrofitModule.class,RepositoryModule.class,DbModule.class})
public interface ApplicationComponent {
    void injectDependencies(Activity activity);
    MoviesRepository getRepo();
}
