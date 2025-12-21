package com.example.cinemasystem.controllers;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie) {
       return movieService.createMovie(movie);
    }

    @GetMapping
    public List<Movie> findAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping(path = "/{id}")
    public Movie findMovie(@PathVariable("id") long id) {
        return movieService.findMovie(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }
}
