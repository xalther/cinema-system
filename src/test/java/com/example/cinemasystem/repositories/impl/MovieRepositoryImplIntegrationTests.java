package com.example.cinemasystem.repositories.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.utils.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MovieRepositoryImplIntegrationTests {

    private final MovieRepositoryImpl underTest;

    @Autowired
    public MovieRepositoryImplIntegrationTests(MovieRepositoryImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatMovieCanBeCreatedAndRecalled() {
        Movie movie = TestDataUtil.createTestMovieA();
        underTest.create(movie);
        Optional<Movie> result = underTest.findOne(movie.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(movie);
    }

    @Test
    public void testThatMultipleMoviesCanBeCreatedAndRecalled() {
        Movie movieA = TestDataUtil.createTestMovieA();
        underTest.create(movieA);
        Movie movieB = TestDataUtil.createTestMovieB();
        underTest.create(movieB);
        Movie movieC = TestDataUtil.createTestMovieC();
        underTest.create(movieC);

        List<Movie> result = underTest.findMany();
        assertThat(result).hasSize(3).containsExactly(movieA, movieB, movieC);
    }

    @Test
    public void testThatMovieCanBeUpdatedAndRecalled() {
        Movie movieA = TestDataUtil.createTestMovieA();
        underTest.create(movieA);

        movieA.setTitle("UPDATED");
        underTest.update(movieA.getId(), movieA);
        Optional<Movie> result = underTest.findOne(movieA.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(movieA);
    }

    @Test
    public void testThatMovieCanBeDeleted() {
        Movie movieA = TestDataUtil.createTestMovieA();
        underTest.create(movieA);

        underTest.delete(movieA.getId());
        Optional<Movie> result = underTest.findOne(movieA.getId());

        assertThat(result).isEmpty();
    }
}
