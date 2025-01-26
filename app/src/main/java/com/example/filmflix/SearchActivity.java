package com.example.filmflix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmflix.adapter.MoviesRvAdapter;
import com.example.filmflix.di.ActivityComponent;
import com.example.filmflix.di.ApplicationComponent;
import com.example.filmflix.di.DaggerActivityComponent;
import com.example.filmflix.di.ViewModelModule;
import com.example.filmflix.viewmodel.SearchViewModel;

public class SearchActivity extends AppCompatActivity implements MoviesRvAdapter.ListItemClickListener{
    ApplicationComponent applicationComponent;
    ActivityComponent activityComponent;
    EditText searchTextBox;
    Handler handler = new Handler();
    Runnable searchRunnable = null;
    SearchViewModel searchViewModel;
    RecyclerView movieGridRecyclerView;
    MoviesRvAdapter moviesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        movieGridRecyclerView = findViewById(R.id.search_grid_recycler_view);
        searchTextBox=findViewById(R.id.search_text_box);
        applicationComponent=((FilmFlixApplication) getApplication()).applicationComponent;
        activityComponent= DaggerActivityComponent.builder().
                applicationComponent(applicationComponent).
                viewModelModule(new ViewModelModule(this)).build();
        searchViewModel=activityComponent.getSearchViewModel();
        setUpTextChangeListener();
        observeSearchResult();
        setUpRecyclerView();
    }
    void setUpRecyclerView(){
        moviesAdapter = new MoviesRvAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        movieGridRecyclerView.setLayoutManager(layoutManager);
        movieGridRecyclerView.setHasFixedSize(true);
        movieGridRecyclerView.setAdapter(moviesAdapter);
    }
    void observeSearchResult(){
        searchViewModel.searchResult.observe(this,movies -> {
            moviesAdapter.updateMoviesList(movies,this);
        });
    }
    void setUpTextChangeListener(){

        searchTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Remove any previously queued runnable
                if (searchRunnable != null) {
                    handler.removeCallbacks(searchRunnable);
                }

                // Create a new Runnable with a delay of 2 seconds
                searchRunnable = () -> {
                    String query = s.toString().trim();
                    if (!query.isEmpty()) {
                        // Call the API
                        searchViewModel.searchMovies(query);
                    }
                };

                // Post the Runnable with a 2-second delay
                handler.postDelayed(searchRunnable, 2000);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onListItemClick(int listItemIndex) {
        Intent intentToStartDetailActivity = new Intent(SearchActivity.this, DetailActivity.class);
        searchViewModel.updateDetailMovie(searchViewModel.searchResult.getValue().get(listItemIndex));
        startActivity(intentToStartDetailActivity);
    }
}