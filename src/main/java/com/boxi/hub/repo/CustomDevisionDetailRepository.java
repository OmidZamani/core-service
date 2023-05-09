package com.boxi.hub.repo;

import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomDevisionDetailRepository extends JpaRepository<CustomDevisionDetail,Long>, JpaSpecificationExecutor<CustomDevisionDetail> {

    List<CustomDevisionDetail> findByCustomCountryDevision(CustomCountryDevision customCountryDevision);

    void deleteByCustomCountryDevision(CustomCountryDevision customCountryDevision);




}
