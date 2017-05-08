package com.hesscom.helloworld.control;

import com.hesscom.helloworld.entity.SimpleDataEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by jhess on 08.05.17.
 */
@ApplicationScoped
public class HelloWorldController {

    @PersistenceContext
    private EntityManager em;

    private Long id = 0L;

    @Transactional
    public boolean store(final String data) {
        id++;
        SimpleDataEntity simple = new SimpleDataEntity(id, data);
        em.persist(simple);
        return true;
    }
}
