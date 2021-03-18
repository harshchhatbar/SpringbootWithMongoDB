package com.example.FirstSpringBoot.api.Repository;

import com.example.FirstSpringBoot.api.Model.Movies;

import java.util.List;

public interface MovieCustomRepo {
    List<Movies> getMoviesByImdbRating(double rating);
}
