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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void putMovies(List<Movie> movies);
}

