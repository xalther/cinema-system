package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.repositories.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
    public Movie create(Movie movie) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(
                    "INSERT INTO movies (title, description, genre, director, production_year) VALUES (?, ?, ?, ?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getDescription());
            ps.setString(3, movie.getGenre());
            ps.setString(4, movie.getDirector());
            ps.setObject(5, movie.getProductionYear());
            return ps;
        }, keyHolder);

        long generatedId = keyHolder.getKey().longValue();
        movie.setId(generatedId);

        return movie;
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
    public boolean update(long id, Movie movie) {
        int updated = jdbcTemplate.update("UPDATE movies SET title = ?, description = ?, genre = ?, director = ?, production_year = ? WHERE id = ?",
                movie.getTitle(), movie.getDescription(), movie.getGenre(), movie.getDirector(), movie.getProductionYear(), id
        );
        return updated > 0;
    }

    @Override
    public boolean delete(long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM movies WHERE id = ?", id);
        return rowsAffected > 0;
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
