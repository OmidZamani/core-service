package com.boxi.transport.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Dock;
import com.boxi.transport.payload.dto.DockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DockRepository extends JpaRepository<Dock, Long>, JpaSpecificationExecutor<Dock> {

    @Modifying
    @Query("update Dock dock set dock.isDeleted = 1 where dock.id = ?1")
    void logicalDelete(Long id);

    Boolean existsByCodeAndIsDeletedFalse(String code);
    Dock findByCodeAndIsDeletedFalse(String code);

    List<Dock> findAllByHubAndIsActiveIsTrueAndIsDeletedIsFalse(Hub hub);

}
