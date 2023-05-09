package com.boxi.PriceList.repo;

import com.boxi.PriceList.entity.Exception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface ExceptionRepository extends JpaRepository<Exception,Long>, JpaSpecificationExecutor<Exception> {
       @Modifying
       @Query("update Exception E set E.isDeleted = 1 where E.Id = ?1")
       void DeleteLogic(Long Id);

       Boolean existsByCode(String Code);


}
