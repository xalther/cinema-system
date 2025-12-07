package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.repositories.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
public class MovieRepositoryImpl implements MovieRepository {
    private final JdbcTemplate jdbcTemplate;

    public MovieRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Movie movie) {
        jdbcTemplate.update(
                "INSERT INTO movies (id, title, description, genre, director, production_year) VALUES(?, ?, ?, ?, ?, ?)",
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getDirector(), movie.getProductionYear()
        );
    }

    @Override
    public Optional<Movie> findOne(long id) {
        List<Movie> results = jdbcTemplate.query("SELECT * FROM movies WHERE id = ? LIMIT 1",
                new MovieRowMapper(), id);
        return results.stream().findFirst();
    }

    @Override
    public List<Movie> findMany() {
        return jdbcTemplate.query("SELECT * FROM movies", new MovieRowMapper());
    }

    @Override
    public void update(long id, Movie movie) {
        jdbcTemplate.update("UPDATE movies SET id = ?, title = ?, description = ?, genre = ?, director = ?, production_year = ? WHERE id = ?",
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getDirector(), movie.getProductionYear(), id
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
    }

    public static class MovieRowMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Movie.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .genre(rs.getString("genre"))
                    .director(rs.getString("director"))
                    .productionYear(rs.getInt("production_year"))
                    .build();
        }
    }
}
