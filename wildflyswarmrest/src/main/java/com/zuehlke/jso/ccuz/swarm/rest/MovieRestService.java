package com.zuehlke.jso.ccuz.swarm.rest;

import com.google.gson.Gson;
import com.zuehlke.jso.ccuz.swarm.repository.MovieRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ccu on 08.05.2017.
 */
@Path("/")
@ApplicationScoped
public class MovieRestService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Inject
    private MovieRepository movieRepository;

    @GET
    @Path("/movie")
    @Produces(MediaType.APPLICATION_JSON)
    public String movie(@QueryParam("id") String id){
        Gson gson = new Gson();
        if(id == null || id.equals("")){
            return gson.toJsonTree(movieRepository.findMovies()).getAsString();
        }
        return gson.toJson(movieRepository.findMovieById(id));
    }
}
