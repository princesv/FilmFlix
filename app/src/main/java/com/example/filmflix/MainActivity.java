package com.example.filmflix;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmflix.adapter.MoviesRvAdapter;
import com.example.filmflix.di.ActivityComponent;
import com.example.filmflix.di.ApplicationComponent;
import com.example.filmflix.di.DaggerActivityComponent;
import com.example.filmflix.di.ViewModelModule;
import com.example.filmflix.models.Movie;
import com.example.filmflix.repository.MoviesRepository;
import com.example.filmflix.viewmodel.MainMoviesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesRvAdapter.ListItemClickListener{
    ApplicationComponent applicationComponent;
    ActivityComponent activityComponent;
    MainMoviesViewModel mainMoviesViewModel;
    RecyclerView movieGridRecyclerView;
    MoviesRvAdapter moviesAdapter;
    LiveData<List<Movie>> movies;
    LiveData<List<Movie>> favouriteMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        movieGridRecyclerView = findViewById(R.id.movie_grid_recycler_view);
        applicationComponent=((FilmFlixApplication) getApplication()).applicationComponent;
        activityComponent= DaggerActivityComponent.builder().
                applicationComponent(applicationComponent).
                viewModelModule(new ViewModelModule(this)).build();
        mainMoviesViewModel=activityComponent.getMainMovieViewModel();
        setUpRecyclerView();
        movies=mainMoviesViewModel.getMovies(this);
        movies.observe(this,movies->{
            if(mainMoviesViewModel.isFav.getValue()==0) {
                moviesAdapter.updateMoviesList(movies, this);
            }
        });
        favouriteMovies=mainMoviesViewModel.getFavouriteMovies();
        favouriteMovies.observe(this,movies->{
            if(mainMoviesViewModel.isFav.getValue()==1){
                moviesAdapter.updateMoviesList(movies, this);
            }
        });
        mainMoviesViewModel.isFav.observe(this,isFav->{
            if(isFav==0){
                List<Movie> movieList=movies.getValue();
                if(movieList!=null){
                    moviesAdapter.updateMoviesList(movieList, this);
                }
            }else{
                List<Movie> movieList=favouriteMovies.getValue();
                if(movieList!=null){
                    moviesAdapter.updateMoviesList(movieList, this);
                }
            }
        });
    }
    void setUpRecyclerView(){
        moviesAdapter = new MoviesRvAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        movieGridRecyclerView.setLayoutManager(layoutManager);
        movieGridRecyclerView.setHasFixedSize(true);
        movieGridRecyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onListItemClick(int listItemIndex) {
        if(mainMoviesViewModel.isFav.getValue()==0) {
            Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
            mainMoviesViewModel.updateDetailMovie(movies.getValue().get(listItemIndex));
            startActivity(intentToStartDetailActivity);
        }else{
            Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
            mainMoviesViewModel.updateDetailMovie(favouriteMovies.getValue().get(listItemIndex));
            startActivity(intentToStartDetailActivity);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int itemId=item.getItemId();
        if(itemId==R.id.popular_movies){
            mainMoviesViewModel.isFav.setValue(0);
        }else{
            mainMoviesViewModel.isFav.setValue(1);
        }
        return true;
    }
}