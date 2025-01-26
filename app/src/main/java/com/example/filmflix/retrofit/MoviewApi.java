package com.example.filmflix.retrofit;


import com.example.filmflix.models.Movie;
import com.example.filmflix.models.MovieList;
import com.example.filmflix.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MoviewApi {
    @GET("3/movie/popular")
    Call<MovieList> getMovies(
            @Query("api_key") String apiKey
    );

    @GET("3/search/movie")
    Call<MovieList> searchMovies(
            @Query("query") String query,
            @Query("include_adult") boolean includeAdult,
            @Query("language") String language,
            @Query("page") int page,
            @Header("Authorization") String authorizationToken
    );
}
