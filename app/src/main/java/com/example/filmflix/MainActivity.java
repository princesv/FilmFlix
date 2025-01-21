package com.example.filmflix;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

import com.example.filmflix.di.ActivityComponent;
import com.example.filmflix.di.ApplicationComponent;
import com.example.filmflix.di.DaggerActivityComponent;
import com.example.filmflix.di.ViewModelModule;
import com.example.filmflix.models.Movie;
import com.example.filmflix.utils.NetworkUtils;
import com.example.filmflix.utils.Utils;
import com.example.filmflix.viewmodel.MainMoviesViewModel;

public class MainActivity extends AppCompatActivity {
    ApplicationComponent applicationComponent;
    MoviesRepository moviesRepository;
    ActivityComponent activityComponent;
    MainMoviesViewModel mainMoviesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        applicationComponent=((FilmFlixApplication) getApplication()).applicationComponent;
        moviesRepository=applicationComponent.getRepo();
        activityComponent= DaggerActivityComponent.builder().
                applicationComponent(applicationComponent).
                viewModelModule(new ViewModelModule(this)).build();
        mainMoviesViewModel=activityComponent.getMainMovieViewModel();
        mainMoviesViewModel.getMovies(this).observe(this,movies->{

        });
    }
}