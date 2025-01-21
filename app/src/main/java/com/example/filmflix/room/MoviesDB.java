package com.example.filmflix.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.filmflix.models.Movie;

import javax.inject.Singleton;

@Singleton
@Database(entities = Movie.class,version = 1)
public abstract class MoviesDB extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
