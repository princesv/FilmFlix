package com.example.filmflix.di;

import com.example.filmflix.di.annotations.ActivityScoped;
import com.example.filmflix.viewmodel.DetailViewModel;
import com.example.filmflix.viewmodel.MainMoviesViewModel;
import com.example.filmflix.viewmodel.SearchViewModel;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ViewModelModule.class)
public interface ActivityComponent {
    MainMoviesViewModel getMainMovieViewModel();
    DetailViewModel getDetailViewModel();
    SearchViewModel getSearchViewModel();
}
