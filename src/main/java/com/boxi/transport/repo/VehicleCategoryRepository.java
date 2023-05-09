package com.boxi.transport.repo;

import com.boxi.transport.entity.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, Long>, JpaSpecificationExecutor<VehicleCategory> {
}
