package com.boxi.trm.repo;

import com.boxi.trm.entity.CalendarHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarHubRepository extends JpaRepository<CalendarHub,Long>, JpaSpecificationExecutor<CalendarHub> {


}
