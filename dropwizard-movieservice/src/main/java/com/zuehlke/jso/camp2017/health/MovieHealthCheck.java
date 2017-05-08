package com.zuehlke.jso.camp2017.health;


import com.codahale.metrics.health.HealthCheck;
import com.zuehlke.jso.camp2017.db.MovieRepository;

public class MovieHealthCheck extends HealthCheck {

    private MovieRepository movieRepository;

    public MovieHealthCheck(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Pseudo-check for demonstration. Checks that the repository returns something without exception
     */
    @Override
    protected Result check() throws Exception {
        movieRepository.get(0);
        return Result.healthy();
    }
}
