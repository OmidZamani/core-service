package com.boxi.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public interface JpaeRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    EntityManager getEntityManager();
}
