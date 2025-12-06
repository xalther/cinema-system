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
    public void testThatCreateMovieGeneratesCorrectSql() {
        Movie movie = TestDataUtil.createTestMovieA();

        underTest.create(movie);

        verify(jdbcTemplate).update(
                eq("INSERT INTO movies (id, title, description, genre, director, production_year) VALUES(?, ?, ?, ?, ?, ?)"),
                eq(1L), eq("Gladiator"), eq("Gladiator movie description"), eq("Action"), eq("Ridley Scot"), eq(2000)
        );
    }

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
}
