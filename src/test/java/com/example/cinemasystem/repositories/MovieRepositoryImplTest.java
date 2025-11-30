package com.example.cinemasystem.repositories;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.repositories.impl.MovieRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MovieRepositoryImpl underTest;

    @Test
    public void testThatCreateMovieGeneratesCorrectSql() {
        Movie movie = Movie.builder()
                .id(1L)
                .title("Gladiator")
                .description("Gladiator movie description")
                .genre("Action")
                .director("Ridley Scot")
                .productionYear(2000)
                .build();

        underTest.create(movie);

        verify(jdbcTemplate).update(
                eq("INSERT INTO movies (id, title, description, genre, director, productionYear) VALUES(?, ?, ?, ?, ?, ?)"),
                eq(1L), eq("Gladiator"), eq("Gladiator movie description"), eq("Action"), eq("Ridley Scot"), eq(2000)
        );
    }
}
