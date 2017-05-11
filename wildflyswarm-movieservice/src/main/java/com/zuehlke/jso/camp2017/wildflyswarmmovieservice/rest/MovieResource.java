package com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest;


import com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest.domain.Movie;
import com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest.domain.MovieDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;


@Path("/movies")
@Produces("application/json")
public class MovieResource {

    @Inject
    private MovieDao movieDao;

    @GET
    public List<Movie> getMovies() {
        return movieDao.getAll();
    }

    @GET
    @Path("{id}")
    public Movie getMovie(@PathParam("id") long id) {
        return movieDao.get(id).orElseThrow(() -> new NotFoundException("Movie with id " + id + " not found!"));
    }

    @POST
    public Movie postMovie(Movie movie) {
        return movieDao.save(movie);
    }
}