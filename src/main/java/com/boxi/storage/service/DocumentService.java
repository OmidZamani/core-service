package com.boxi.storage.service;

import com.boxi.core.request.SimpleWrapper;
import com.boxi.storage.dto.FileMeta;
import com.boxi.storage.entity.Document;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface DocumentService {

     Document getFile(String fileId);

     List<FileMeta> setContents(MultipartHttpServletRequest request,String folderName);
}
