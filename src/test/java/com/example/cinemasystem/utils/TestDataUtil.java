package com.example.cinemasystem.utils;

import com.example.cinemasystem.domain.Movie;

public abstract class TestDataUtil {
    public static Movie createTestMovieA() {
        return Movie.builder()
                .id(1L)
                .title("Gladiator")
                .description("Gladiator movie description")
                .genre("Action")
                .director("Ridley Scot")
                .productionYear(2000)
                .build();
    }
    public static Movie createTestMovieB() {
        return Movie.builder()
                .id(2L)
                .title("Green mile")
                .description("Green mile movie description")
                .genre("Drama")
                .director("Frank Darobont")
                .productionYear(2000)
                .build();
    }
    public static Movie createTestMovieC() {
        return Movie.builder()
                .id(3L)
                .title("Lord of the rings")
                .description("Lord of the rings movie description")
                .genre("Action")
                .director("Peter Jackson")
                .productionYear(2000)
                .build();
    }
}
