package com.example.cinemasystem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private String director;
    private Integer productionYear;
}
