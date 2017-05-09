package com.cbo.service;

import com.cbo.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cbo on 09.05.17.
 */
@ApplicationScoped
public class MovieService {

  @PersistenceContext(name = "moviePU")
  private EntityManager em;

  @Transactional
  public List<Movie> getMovies() {
    List<Movie> movies = em.createNamedQuery(Movie.getAll, Movie.class).getResultList();
    return movies;
  }

  @Transactional
  public Movie getMovie(Long id) {
    Movie movie = em.createNamedQuery(Movie.getById, Movie.class).setParameter("id",id).getSingleResult();

    return movie;
  }

  @Transactional
  public void addMovie(Movie movie) {
    em.persist(movie);
  }

}
