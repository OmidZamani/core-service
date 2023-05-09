package com.boxi.transport.repo;

import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.entity.VehicleExceptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.Date;
import java.util.List;

public interface VehicleExceptionsRepository extends JpaRepository<VehicleExceptions,Long>  , JpaSpecificationExecutor<VehicleExceptions> {
    List<VehicleExceptions> findAllByCreatedDateAndVehicle(Date createDate, Vehicle vehicle);
}
