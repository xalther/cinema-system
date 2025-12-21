package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.utils.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MovieRepositoryImpl underTest;


    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT * FROM movies WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<MovieRepositoryImpl.MovieRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        underTest.findMany();

        verify(jdbcTemplate).query(
                eq("SELECT * FROM movies"),
                ArgumentMatchers.<MovieRepositoryImpl.MovieRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateMovieGeneratesCorrectSql() {
        Movie movie = TestDataUtil.createTestMovieA();

        underTest.update(2L, movie);

        verify(jdbcTemplate).update(
                eq("UPDATE movies SET title = ?, description = ?, genre = ?, director = ?, production_year = ? WHERE id = ?"),
                eq("Gladiator"), eq("Gladiator movie description"), eq("Action"), eq("Ridley Scot"), eq(2000), eq(2L)
        );
    }

    @Test
    public void testThatDeleteMovieGeneratesCorrectSql() {
        underTest.delete(1L);

        verify(jdbcTemplate).update(
                eq("DELETE FROM movies WHERE id = ?"),
                eq(1L)
        );
    }
}
