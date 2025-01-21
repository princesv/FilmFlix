package com.example.filmflix.di;

import com.example.filmflix.di.annotations.ActivityScoped;
import com.example.filmflix.viewmodel.MainMoviesViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ViewModelModule.class)
public interface ActivityComponent {
    MainMoviesViewModel getMainMovieViewModel();
}
