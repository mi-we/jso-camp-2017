package com.cbo.springmovie.service;

import com.cbo.springmovie.entity.Movie;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cbo on 11.05.17.
 */
@Service
public class MovieService {

  private Map<Long, Movie> movieMap = new HashMap<>();
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public Movie getMovie(long id) {
    return em.createNamedQuery(Movie.GET, Movie.class).setParameter("id", id).getSingleResult();
  }

  @Transactional
  public List<Movie> getMovies() {
    return em.createNamedQuery(Movie.GET_ALL, Movie.class).getResultList();
  }

  @Transactional
  public void add(Movie movie) {
    em.persist(movie);
  }

}
