package com.example.filmflix.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.filmflix.models.Movie;
import com.example.filmflix.models.MovieList;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void putMovies(List<Movie> movies);

    @Query("SELECT * FROM Movie WHERE isFavourite=true")
    LiveData<List<Movie>> getFavouriteMovies();

    @Query("UPDATE Movie SET isFavourite = :isFavourite WHERE id = :movieId")
    void updateMovieFavoriteStatus(int movieId, boolean isFavourite);

}

