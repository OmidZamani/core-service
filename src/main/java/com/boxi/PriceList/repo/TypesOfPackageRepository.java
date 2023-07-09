package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.TypesOfPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypesOfPackageRepository extends JpaRepository<TypesOfPackage, Long>, JpaSpecificationExecutor<TypesOfPackage> {
}
