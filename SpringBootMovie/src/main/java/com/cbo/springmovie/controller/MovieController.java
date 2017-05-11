package com.cbo.springmovie.controller;

import com.cbo.springmovie.entity.Movie;
import com.cbo.springmovie.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by cbo on 10.05.17.
 */
@RestController
public class MovieController {

  private final AtomicLong counter = new AtomicLong();

  @Autowired
  private MovieService service;


  @ApiOperation(value = "getMovie", nickname = "getMovie")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = MovieController.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  @GetMapping(path="/movie/{id}", produces = "application/json")
  public Movie getMovie(@PathVariable("id") @ApiParam("id") long id) {
    return service.getMovie(id);
  }

  @ApiOperation(value = "getMovie", nickname = "getMovie")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = MovieController.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  @GetMapping(path="/movie", produces = "application/json")
  public List<Movie> getMovie() {
    return service.getMovies();
  }

  @ApiOperation(value = "putMovie", nickname = "putMovie")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = MovieController.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")})
  @PutMapping(path="/movie/{name}/{year}")
  public void putMovie(@PathVariable("name") @ApiParam("name") String name, @PathVariable("year") @ApiParam("year")long year ) {
    service.add(new Movie(counter.incrementAndGet(), name, year));
  }

}
