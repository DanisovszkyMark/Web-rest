package com.example.webrest.rest.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDatabaseManager {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
