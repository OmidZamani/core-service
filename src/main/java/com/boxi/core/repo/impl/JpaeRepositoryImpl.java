package com.boxi.core.repo.impl;

import com.boxi.core.repo.JpaeRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;


public class JpaeRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaeRepository<T, ID> {

    protected EntityManager entityManager;

    public JpaeRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}