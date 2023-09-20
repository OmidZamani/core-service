package com.boxi.transport.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Vehicle;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.enums.VehicleType;
import com.boxi.transport.payload.dto.VehicleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {

    boolean existsByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(String vehicleNumber0, String vehicleNumber1, String vehicleNumber2, String vehicleNumber3);

    Vehicle findByVehicleNumber0AndVehicleNumber1AndVehicleNumber2AndVehicleNumber3(String vehicleNumber0, String vehicleNumber1, String vehicleNumber2, String vehicleNumber3);

    List<Vehicle> findByVehicleNumber0OrVehicleNumber1OrVehicleNumber2OrVehicleNumber3(String vehicleNumber0, String vehicleNumber1, String vehicleNumber2, String vehicleNumber3);

    @Modifying
    @Query("update Vehicle v set v.isDeleted = 1 where v.id = ?1")
    void logicalDelete(Long id);

    List<Vehicle> findAllByTypeAndHub(VehicleType vehicleType, Hub hub);

    List<Vehicle> findAllByVehicleMakeAndHub(VehicleMake vehicleMake, Hub hub);

    List<Vehicle> findAllByVendorAndIsActiveIsTrueAndIsDeletedIsFalse(Vendor vendor);

    Vehicle findTopByFirstDriverIdOrSecondDriverIdOrderByIdDesc(Long firstDriverId, Long secondDriverId);


}
