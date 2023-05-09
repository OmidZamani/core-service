package com.boxi.transport.repo;

import com.boxi.transport.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.lang.Nullable;

import java.util.Optional;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>, JpaSpecificationExecutor<Vendor> {
    Boolean existsByCodeAndIsDeletedFalse(String code);
    Boolean existsByNationalCodeAndIsDeletedFalse(String code);

    @Modifying
    @Query("update Vendor v set v.isDeleted = 1 where v.id = ?1")
    void logicalDelete(Long id);

    Vendor findByCode(String vendorSelect);

/*     default Optional<Vendor> findByName(@Nullable String name) {
          return Optional.ofNullable(name)
                  .flatMap(n -> getEntityManager().createNativeQuery(("select * from ")));*/

}
