package com.example.filmflix.retrofit;


import com.example.filmflix.models.MovieList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface MoviewApi {
    @GET("popular?api_key=41ec4d909d71953ad8ffa75eb3157315")
    Call<MovieList> getMovies();
}
