package com.boxi.transport.repo;
import com.boxi.transport.entity.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long>, JpaSpecificationExecutor<VehicleMake> {
    @Modifying
    @Query("update VehicleMake v set v.isDeleted = 1 where v.id = ?1")
    void logicalDelete(Long id);

    boolean existsByCodeAndIsDeletedFalse(String code);

    VehicleMake findByCode(String vehicleMakeSelect);
}
