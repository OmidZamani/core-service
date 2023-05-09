package com.boxi.transport.repo;

import com.boxi.transport.entity.BagExceptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BagExceptionsRepository extends JpaRepository<BagExceptions, Long>, JpaSpecificationExecutor<BagExceptions> {
}
