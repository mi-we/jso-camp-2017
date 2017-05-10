package com.zuehlke.jso.camp2017.web;

import com.zuehlke.jso.camp2017.domain.Movie;
import com.zuehlke.jso.camp2017.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @PostMapping
    public Movie postMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable(name = "id") Long id) {
        return movieRepository.findOne(id);
    }
}
