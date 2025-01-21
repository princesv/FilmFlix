package com.example.filmflix;

import android.app.Application;

import androidx.lifecycle.ViewModelStoreOwner;

import com.example.filmflix.di.ApplicationComponent;
import com.example.filmflix.di.DaggerApplicationComponent;
import com.example.filmflix.di.DbModule;
import com.example.filmflix.di.ViewModelModule;

public class FilmFlixApplication extends Application {
    ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent=DaggerApplicationComponent.builder().dbModule(new DbModule(this)).build();
    }
}
