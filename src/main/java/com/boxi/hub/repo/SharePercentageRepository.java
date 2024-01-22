package com.boxi.hub.repo;

import com.boxi.hub.entity.SharePercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SharePercentageRepository extends JpaRepository<SharePercentage,Long> , JpaSpecificationExecutor<SharePercentage> {

}
