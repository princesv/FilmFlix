package com.example.filmflix.models;

import java.util.List;

public class MovieList {
    private int page;
    private List<Movie> results;

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }

}
