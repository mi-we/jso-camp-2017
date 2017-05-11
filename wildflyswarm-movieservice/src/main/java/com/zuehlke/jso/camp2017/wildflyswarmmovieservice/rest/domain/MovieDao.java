package com.zuehlke.jso.camp2017.wildflyswarmmovieservice.rest.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieDao {

    @PersistenceContext
    private EntityManager em;

    public List<Movie> getAll() {
        return em.createNamedQuery(Movie.GET_ALL, Movie.class).getResultList();
    }

    public Optional<Movie> get(long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Transactional
    public Movie save(Movie movie) {
        Movie newMovie = new Movie(0, movie.getTitle(), movie.getYear());
        em.persist(newMovie);
        return newMovie;
    }

}
