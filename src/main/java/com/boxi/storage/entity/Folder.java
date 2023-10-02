package com.boxi.storage.entity;


import com.boxi.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="TBL_FOLDER")
public class Folder  extends BaseEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PK_FOLDER_ID", nullable=false, insertable=true, updatable=true)
    private Long id;

    private String folderName;


}
