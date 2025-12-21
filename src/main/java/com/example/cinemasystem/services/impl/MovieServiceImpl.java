package com.example.cinemasystem.services.impl;

import com.example.cinemasystem.domain.Movie;
import com.example.cinemasystem.repositories.MovieRepository;
import com.example.cinemasystem.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.create(movie);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findMany();
    }

    @Override
    public Movie findMovie(long id) {
        return movieRepository.findOne(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie updateMovie(long id, Movie movie) {
        boolean updated = movieRepository.update(id, movie);
        if (!updated) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        movie.setId(id);
        return movie;
    }

    @Override
    public void deleteMovie(long id) {
        boolean deleted = movieRepository.delete(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
