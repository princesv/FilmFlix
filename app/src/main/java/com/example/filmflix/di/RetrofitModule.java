package com.example.filmflix.di;

import com.example.filmflix.retrofit.MoviewApi;
import com.example.filmflix.utils.Utils;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    Retrofit getRetrofitInstance(){
        // Build OkHttpClient
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL) // Attach OkHttpClient
                .addConverterFactory(GsonConverterFactory.create()) // Add JSON converter
                .build();
    }
    @Provides
    MoviewApi getMoviesApi(Retrofit retrofit){
        return retrofit.create(MoviewApi.class);
    }
}
