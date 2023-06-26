package com.boxi.ruleEngine.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name="TBL_FACTLOOKUP")
@Data
@Accessors(chain = true)
public class FactLookup {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PK_FACT_LOOKUP_ID", insertable=true, updatable=true)
    private Long id;

    private String name;

    private String code;

    private String valuesIfAvailable;

    private String description;

    private String serviceNameIfAvailable;


    @Basic(optional=false)
    @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
    private Boolean isActive;


}
