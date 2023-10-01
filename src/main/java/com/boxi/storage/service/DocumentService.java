package com.boxi.storage.service;

import com.boxi.core.request.SimpleWrapper;
import com.boxi.crm.payload.converter.SalesChannelConverterImpl;
import com.boxi.storage.entity.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

     Document getFile(String fileId);

     SimpleWrapper setContent(byte[] bytes, String contentType, String folder, String fName);
}
