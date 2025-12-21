package com.example.cinemasystem.repositories;

import com.example.cinemasystem.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Movie create(Movie movie);

    Optional<Movie> findOne(long id);

    List<Movie> findMany();

    boolean update(long id, Movie movie);

    boolean delete(long id);
}
