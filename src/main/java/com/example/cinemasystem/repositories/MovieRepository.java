package com.example.cinemasystem.repositories;

import com.example.cinemasystem.domain.Movie;

import java.util.Optional;

public interface MovieRepository {
    void create(Movie movie);

    Optional<Movie> findOne(long id);
}
