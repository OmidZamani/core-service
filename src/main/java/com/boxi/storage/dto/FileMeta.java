package com.boxi.storage.dto;

import com.boxi.storage.entity.Folder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileMeta {

    private String id;

    private String fileName;

    private String mimeType;

    private String fileSize;

    private String extension;

    private String folderName; //TYPES AS FOLDERS
}
