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
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("filedId")  String fileId) throws Exception
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


/*    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }*/
}
