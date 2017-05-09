package com.zuehlke.jso.camp2017.db;


import com.google.common.collect.ImmutableList;
import com.zuehlke.jso.camp2017.api.Movie;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MovieRepository {

    private MovieDAO movieDAO;

    public MovieRepository(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public List<Movie> getAll() {
        return movieDAO.findAll();
    }

    public Optional<Movie> get(long id) {
        return Optional.of(movieDAO.findById(id));
    }

    public Movie save(Movie movie) {
        movieDAO.insert(movie.getTitle(), movie.getYear());
        return movie;
    }

    public void clear() {
        movieDAO.deleteAll();
    }
}
