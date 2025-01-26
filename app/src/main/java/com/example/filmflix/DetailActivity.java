package com.example.filmflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.filmflix.di.ActivityComponent;
import com.example.filmflix.di.ApplicationComponent;
import com.example.filmflix.di.DaggerActivityComponent;
import com.example.filmflix.di.ViewModelModule;
import com.example.filmflix.models.Movie;
import com.example.filmflix.viewmodel.DetailViewModel;
import com.example.filmflix.viewmodel.DetailViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ApplicationComponent applicationComponent;
    ActivityComponent activityComponent;
    DetailViewModel detailViewModel;
    TextView tvMovieTitle;
    TextView tvMovieOverview;
    TextView tvMovieRating;
    TextView tvMovieTotalVotes;
    TextView tvMovieOriginalLangudge;
    TextView tvOriginalTitle;
    TextView tvMovieIsAdult;
    TextView tvDateOfRelease;
    ImageView ivBackdropImage;
    ImageView ivposterImage;
    FloatingActionButton fab;
    MutableLiveData<Movie> movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intentThatStartedActivity =getIntent();
        tvMovieTitle = findViewById(R.id.movieTitle);
        tvMovieOverview =findViewById(R.id.overview);
        tvMovieRating = findViewById(R.id.movieRating);
        tvDateOfRelease = findViewById(R.id.dateOfRelease);
        tvMovieTotalVotes = findViewById(R.id.voteCount);
        tvOriginalTitle = findViewById(R.id.originalTitle);
        tvMovieOriginalLangudge= findViewById(R.id.originalLanguage);
        tvMovieIsAdult = findViewById(R.id.isAdult);
        ivBackdropImage = findViewById(R.id.backdropImage);
        ivposterImage = findViewById(R.id.posterImage);
        fab=findViewById(R.id.fab);
        applicationComponent=((FilmFlixApplication) getApplication()).applicationComponent;
        activityComponent= DaggerActivityComponent.builder().
                applicationComponent(applicationComponent).
                viewModelModule(new ViewModelModule(this)).build();
        detailViewModel=activityComponent.getDetailViewModel();
        movie=detailViewModel.getMovieDetail();
        movie.observe(this,movie->{
            if(movie.isFavourite()==true){
                fab.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.heart_liked));
            }else{
                fab.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.heart_not_liked));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie currMovie=movie.getValue();
                if(movie.getValue().isFavourite()==false){
                    detailViewModel.switchFavouriteStatus(movie.getValue(),true);
                    detailViewModel.updateMovie(new Movie(currMovie,true));
                }else{
                    detailViewModel.switchFavouriteStatus(movie.getValue(),false);
                    detailViewModel.updateMovie(new Movie(currMovie,false));
                }
            }
        });
        updateUI();
    }
    void updateUI(){
        tvMovieIsAdult.setText(""+movie.getValue().isAdult());
        tvMovieTitle.setText(movie.getValue().getTitle());
        tvMovieOriginalLangudge.setText(movie.getValue().getOriginal_language());
        tvOriginalTitle.setText(movie.getValue().getOriginal_title());
        tvMovieOverview.setText(movie.getValue().getOverview());
        tvMovieTotalVotes.setText(""+movie.getValue().getVote_count());
        tvDateOfRelease.setText(""+movie.getValue().getRelease_date());
        tvMovieRating.setText(""+movie.getValue().getVote_average());
        Picasso.get().load("http://image.tmdb.org/t/p/w185//"+movie.getValue().getPoster_path()).into(ivposterImage);
        Picasso.get().load("http://image.tmdb.org/t/p/w185//"+movie.getValue().getBackdrop_path()).into(ivBackdropImage);
    }

}