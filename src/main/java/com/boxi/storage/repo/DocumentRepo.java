package com.boxi.storage.repo;


import com.boxi.storage.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document,String>, JpaSpecificationExecutor<Document> {
}
