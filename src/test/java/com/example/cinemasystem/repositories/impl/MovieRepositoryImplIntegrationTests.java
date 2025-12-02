package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.utils.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieRepositoryImplIntegrationTests {

    private MovieRepositoryImpl underTest;

    @Autowired
    public MovieRepositoryImplIntegrationTests(MovieRepositoryImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatMovieCanBeCreatedAndRecalled() {
        Movie movie = TestDataUtil.createTestMovie();
        underTest.create(movie);
        Optional<Movie> result = underTest.findOne(movie.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(movie);
    }
}
