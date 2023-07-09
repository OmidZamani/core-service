package com.boxi.ruleEngine.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TBL_RULEMODEL")
@Data
@ToString
@Accessors(chain = true)
public class RuleModel extends BaseEntity
        implements java.io.Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PK_RULE_MODEL_ID", insertable=true, updatable=true)
    private Long id;

    private int priority;


    private String condition;


    private String action;

    //...........................


    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @Column(nullable = false)
    @Lob
    @NotBlank
    private String content;

    private String description;

    @Basic(optional=false)
    @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true, length=1)
    private Boolean isActive;

    @Basic(optional=true)
    @Column(name="ISDELETED", insertable=true, updatable=true, length=1)
    private Boolean isDeleted;



}
