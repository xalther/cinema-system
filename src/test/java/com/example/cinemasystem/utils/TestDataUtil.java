package com.example.cinemasystem.utils;

import com.example.cinemasystem.domain.Movie;

public abstract class TestDataUtil {
    public static Movie createTestMovie() {
        return Movie.builder()
                .id(1L)
                .title("Gladiator")
                .description("Gladiator movie description")
                .genre("Action")
                .director("Ridley Scot")
                .productionYear(2000)
                .build();
    }
}
