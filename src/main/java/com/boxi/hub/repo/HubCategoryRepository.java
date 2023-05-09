package com.boxi.hub.repo;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.projection.CategoryHubSelectView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HubCategoryRepository extends JpaRepository<HubCategory, Long>, JpaSpecificationExecutor<HubCategory> {
    Boolean existsByName(String name);

   // List<HubCategory> findByNameLike(String name);

    List<CategoryHubSelectView>  findByNameLike(String name);
    HubCategory findTopByNameLike(String cat);

    @Modifying
    @Query("update HubCategory c set c.isDeleted = 1 where c.id = ?1")
    void logicalDelete(Long id);
}
