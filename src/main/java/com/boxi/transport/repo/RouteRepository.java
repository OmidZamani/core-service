package com.boxi.transport.repo;

import com.boxi.transport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RouteRepository extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
    Optional<Route> findTopByCode(String code);

    Boolean existsByCodeAndIsDeletedFalse(String code);

/*     default Optional<Vendor> findByName(@Nullable String name) {
          return Optional.ofNullable(name)
                  .flatMap(n -> getEntityManager().createNativeQuery(("select * from ")));*/

}
