package com.boxi.storage.repo;


import com.boxi.storage.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FolderRepo extends JpaRepository<Folder, Long> {
    Folder findByFolderName(String folderName);
}