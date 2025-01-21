package com.example.filmflix.di;

import android.content.Context;

import androidx.room.Room;

import com.example.filmflix.di.annotations.ApplicationScoped;
import com.example.filmflix.room.MoviesDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    Context context;
    public DbModule(Context context){
        this.context=context;
    }
    @ApplicationScoped
    @Provides
    MoviesDB getMoviesDb(){
        return Room.databaseBuilder(context,MoviesDB.class,"MoviesDB").build();
    }
}
