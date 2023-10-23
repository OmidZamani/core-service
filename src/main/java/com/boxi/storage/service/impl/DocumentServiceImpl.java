package com.boxi.storage.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.SimpleWrapper;
import com.boxi.storage.dto.FileMeta;
import com.boxi.storage.entity.Document;
import com.boxi.storage.entity.Folder;
import com.boxi.storage.repo.DocumentRepo;
import com.boxi.storage.repo.FolderRepo;
import com.boxi.storage.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {


    private final DocumentRepo documentRepo;

    private final FolderRepo folderRepo;

    @Autowired
    public DocumentServiceImpl(DocumentRepo documentRepo, FolderRepo folderRepo) {
        this.documentRepo = documentRepo;
        this.folderRepo = folderRepo;
    }



    public FileMeta saveContent(byte[] bytes, String contentType, String folderName, String fileName,String extension,String fileSize) {
            Document document = new Document();
          //  Blob content = new SerialBlob(file.getBytes());
            document.setData(bytes);
            Folder folder = createFolderIfNotExist(folderName);
            document.setFolder(folder);//Each folder represent diff action for example pickup,delivery
            document.setMimeType(contentType);
            document.setFileName(fileName);
            document.setFileSize(fileSize);
            document.setExtension(extension);
            Document saved = documentRepo.save(document);
            FileMeta fileMeta = new FileMeta();
            BeanUtils.copyProperties(saved,fileMeta);
            fileMeta.setFolderName(folder.getFolderName());
            return fileMeta;
    }

    @Override
    public List<FileMeta> setContents(MultipartHttpServletRequest request,String folderName) {

        Iterator<String> filenamesItr = request.getFileNames();
        MultipartFile mpf = null;
        String extension = "";
        String fName = "";

        List<FileMeta> metas=new ArrayList<>();

        while (filenamesItr.hasNext()) {
            mpf = request.getFile(filenamesItr.next());

            String originalFileName = null;
            if (mpf != null) {
                originalFileName = mpf.getOriginalFilename();
            }
            else{
                throw BusinessException.valueException(EntityType.Document, "File.name.can.not.null");
            }
            int idxOfDot = 0;
            if (originalFileName != null) {
                idxOfDot = originalFileName.lastIndexOf('.');
                extension = originalFileName.substring(idxOfDot + 1);
            }

            String fileSize = mpf.getSize() / 1024 + " Kb";
             /* if (extension.equals("jpg")) {
                createThumb(fName, fPath);
                createMedium(name, fPath);
            }*/
            try {
                metas.add(saveContent(mpf.getBytes(), mpf.getContentType(), folderName, originalFileName, extension, fileSize));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return metas;
    }

    @Override
    public Document getFile(String fileId) {
        return documentRepo.findById(fileId)
                .orElseThrow( ()-> { throw BusinessException.valueException(EntityType.EXCEPTION, "exception.not.found");});
    }

    public Folder createFolderIfNotExist(String folderName){
      Folder folder=folderRepo.findByFolderName(folderName);
      if(folder==null){
          Folder newFolder=new Folder();
          newFolder.setFolderName(folderName);
          return folderRepo.save(newFolder);
      }
      return folder;
    }
}
