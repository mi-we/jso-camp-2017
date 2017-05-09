package com.zuehlke.jso.camp2017.resources;

import com.codahale.metrics.annotation.Timed;
import com.zuehlke.jso.camp2017.api.Movie;
import com.zuehlke.jso.camp2017.db.MovieRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Timed
@Path("movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    private MovieRepository movieRepository;

    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    public List<Movie> getMovies() {
        return movieRepository.getAll();
    }

    @GET
    @Path("{id}")
    public Movie getMovie(@PathParam("id") long id) {
        return movieRepository.get(id).orElseThrow(() -> new NotFoundException("Movie with id " + id + " not found"));
    }

    @POST
    public Movie postMovie(Movie movie) {
        if (movie == null) {
            throw new BadRequestException("No resource provided to create!");
        }
        return movieRepository.save(movie);
    }
}