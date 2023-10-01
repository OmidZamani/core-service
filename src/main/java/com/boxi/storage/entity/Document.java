package com.boxi.storage.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;
import java.util.UUID;

@Data
@Entity
@Table(name="TBL_DOCUMENT")
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String mimeType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="FK_FOLDER_ID", referencedColumnName="PK_FOLDER_ID", nullable=true)
    private Folder folder; //TYPES AS FOLDERS

    @Lob
    private byte[] data;
    //BLOB data


}
