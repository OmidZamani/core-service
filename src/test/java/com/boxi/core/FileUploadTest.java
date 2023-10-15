package com.boxi.core;

import com.boxi.core.request.SimpleWrapper;
import com.boxi.storage.service.DocumentService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadTest {

    @Autowired
    DocumentService documentService;


/*    @Test()
    @DisplayName("insert file")
    public void uploadTest() {
        SimpleWrapper saved = documentService.setContents("data".getBytes(StandardCharsets.UTF_8), "txt", "myFolder", "fileName");
        assertNotNull(saved.getIn());
    }*/
}
