package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.repositories.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class MovieRepositoryImpl implements MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Movie movie) {
        jdbcTemplate.update(
                "INSERT INTO movies (id, title, description, genre, director, productionYear) VALUES(?, ?, ?, ?, ?, ?)",
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getDirector(), movie.getProductionYear()
        );
    }
}
