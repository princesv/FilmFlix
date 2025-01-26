package com.example.filmflix.utils;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    public static final String BASE_URL="http://api.themoviedb.org/";
    public static final String apiKey="41ec4d909d71953ad8ffa75eb3157315";
    public static final String AUTH_TOKEN="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MWVjNGQ5MDlkNzE5NTNhZDhmZmE3NWViMzE1NzMxNSIsIm5iZiI6MTYwNDY1NzcwMi40MTEsInN1YiI6IjVmYTUyMjI2MWI3Mjk0MDAzYjhmYjgzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.uPW9sAKGMfGuhqTMk_uoO70RcC5FEygxajVN6c2bknk";
}
