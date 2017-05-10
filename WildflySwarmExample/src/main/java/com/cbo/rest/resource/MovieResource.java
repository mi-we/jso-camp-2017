package com.cbo.rest.resource;

import com.cbo.entity.Movie;
import com.cbo.service.MovieService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by cbo on 08.05.17.
 */
@Path("/movie")
public class MovieResource {

  private static Logger logger = Logger.getLogger(MovieResource.class);

  @Inject
  private MovieService movieService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Movie> getMovies() {
    List<Movie> movies = movieService.getMovies();
    return movies;
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Movie getMovie(@PathParam("id") Long id) {
    Movie movie = movieService.getMovie(id);

    return movie;
  }

  @POST
  @Path("res")
  @Produces("text/plain")
  public Response doPost() {
    logger.info("POST CALLED");
    return Response.ok("Das war ein Post").build();
  }

  @PUT
  @Path("{id}/{name}/{year}")
  public Response putMovie(@PathParam("id") Long id, @PathParam("name") String name, @PathParam("year") int year) {
    movieService.addMovie(new Movie(id, name, year));
    logger.error("Ach einfach ein check ob auch zwei Logger möglich sind");
    return Response.ok().build();
  }


}
