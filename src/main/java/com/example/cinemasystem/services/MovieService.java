package com.example.cinemasystem.services;

import com.example.cinemasystem.domain.Movie;

import java.util.List;


public interface MovieService {
    Movie createMovie(Movie movie);

    List<Movie> findAllMovies();

    Movie findMovie(long id);

    Movie updateMovie(long id, Movie movie);

    void deleteMovie(long id);
}
