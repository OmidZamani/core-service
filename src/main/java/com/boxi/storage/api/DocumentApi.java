package com.boxi.storage.api;


import com.boxi.storage.dto.FileMeta;
import com.boxi.storage.entity.Document;
import com.boxi.storage.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.io.impl.ByteArrayResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/core-api/storage")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class DocumentApi {

    @Autowired
    DocumentService documentService;


    @RequestMapping(value = "/uploads", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    ResponseEntity<List<FileMeta>> upload(MultipartHttpServletRequest request,
                                          @RequestBody MultiValueMap<String, String> formData,
                                          HttpServletResponse response) {
        // or   Map<String, String[]> parameterMap = request.getParameterMap();

        formData.forEach((key, value) -> System.out.println(key + " " + value));
        String folderName = "---";
        if (request.getParameter("folderName") != null)
            folderName = request.getParameter("folderName");

        log.warn(">>>>>>>>>>>>" + folderName);
        List<FileMeta> metas = documentService.setContents(request, folderName);

        return new ResponseEntity(metas, HttpStatus.OK);

    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("filedId") String fileId) throws Exception {
        try {
            Document document = documentService.getFile(fileId);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                    .body(new ByteArrayResource(document.getData()));
        } catch (Exception e) {
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
