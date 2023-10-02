package com.boxi.storage.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.SimpleWrapper;
import com.boxi.storage.entity.Document;
import com.boxi.storage.entity.Folder;
import com.boxi.storage.repo.DocumentRepo;
import com.boxi.storage.repo.FolderRepo;
import com.boxi.storage.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Blob;

@Service
public class DocumentServiceImpl implements DocumentService {


    private final DocumentRepo documentRepo;

    private final FolderRepo folderRepo;

    @Autowired
    public DocumentServiceImpl(DocumentRepo documentRepo, FolderRepo folderRepo) {
        this.documentRepo = documentRepo;
        this.folderRepo = folderRepo;
    }

    @Override
    public SimpleWrapper setContent(byte[] bytes, String contentType, String folder, String fileName) {
        try {
            Document document = new Document();
          //  Blob content = new SerialBlob(file.getBytes());
            document.setData(bytes);
            document.setFolder(folderRepo.findByFolderName(folder));//Each folder represent diff action for example pickup,delivery
            document.setMimeType(contentType);
            document.setFileName(fileName);
            Document saved = documentRepo.save(document);

            return new SimpleWrapper().setIn(saved.getId().toString());
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Document getFile(String fileId) {
        return documentRepo.findById(fileId)
                .orElseThrow( ()-> { throw BusinessException.valueException(EntityType.EXCEPTION, "exception.not.found");});
    }

    private Folder createFolderIfNotExist(String folderName){
      Folder folder=folderRepo.findByFolderName(folderName);
      if(folder==null){
          Folder newFolder=new Folder();
          newFolder.setFolderName(folderName);
          folderRepo.save(newFolder);
          return folderRepo.save(newFolder);
      }
      return folder;
    }
}
