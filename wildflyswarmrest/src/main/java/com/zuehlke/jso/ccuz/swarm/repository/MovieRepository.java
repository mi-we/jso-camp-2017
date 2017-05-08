package com.zuehlke.jso.ccuz.swarm.repository;

import com.zuehlke.jso.ccuz.swarm.repository.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ccu on 08.05.2017.
 */
@ApplicationScoped
public class MovieRepository {
    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Movie> findMovies() {
        return getEntityManager().createNamedQuery("Movie.findAll", Movie.class).getResultList();
    }

    public Movie findMovieById(String movieId) {
        TypedQuery<Movie> q2 =
                getEntityManager().createQuery("SELECT m FROM Movie m", Movie.class);
        return q2.setParameter("id", movieId).getSingleResult();
    }
}
