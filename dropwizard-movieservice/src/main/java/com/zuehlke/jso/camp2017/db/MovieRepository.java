package com.zuehlke.jso.camp2017.db;


import com.google.common.collect.ImmutableList;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MovieRepository {

    private AtomicLong atomicId;
    private Map<Long, Movie> movies;

    public MovieRepository() {
        atomicId = new AtomicLong(1);
        movies = new LinkedHashMap<>();
    }

    public List<Movie> getAll() {
        return ImmutableList.copyOf(movies.values());
    }

    public Optional<Movie> get(long id) {
        return Optional.of(movies.get(id));
    }

    public Movie save(Movie movie) {
        long nextId = atomicId.getAndIncrement();
        Movie newMovie = new Movie(nextId, movie.getTitle(), movie.getYear());
        movies.put(nextId, newMovie);
        return newMovie;
    }
}
