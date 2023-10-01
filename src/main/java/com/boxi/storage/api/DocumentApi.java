package com.boxi.storage.api;

import com.boxi.core.response.Response;
import com.boxi.storage.entity.Document;
import com.boxi.storage.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.io.impl.ByteArrayResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/core-api/storage")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class DocumentApi {

    @Autowired
    DocumentService documentService;

    @PostMapping("/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("folder") String folder,@RequestParam("fileName") String fileName) {
        String message = "";
        try {
            String fName = fileName != null ? fileName : file.getOriginalFilename();
            if(file==null){
                return Response.exception().setPayload("file Can not be empty");
            }
            documentService.setContent(file.getBytes(),file.getContentType(), folder, fName);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return Response.exception().setPayload(message);
        }
        return Response.ok();

    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(String fileId) throws Exception
    {
        try
        {
            Document document = documentService.getFile(fileId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                    .body(new ByteArrayResource(document.getData()));
        }
        catch(Exception e)
        {
            throw new Exception("Error downloading file");
        }
    }
}
