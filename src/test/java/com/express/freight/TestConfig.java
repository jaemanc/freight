package com.express.freight;

import org.springframework.boot.test.context.TestConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

}
