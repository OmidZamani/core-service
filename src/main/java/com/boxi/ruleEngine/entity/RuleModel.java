package com.boxi.ruleEngine.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RuleModel extends BaseEntity
        implements java.io.Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PK_RULE_MODEL_ID", insertable=true, updatable=true)
    private Long id;

    private int priority;

    private String name;

    private String code;

    @Lob
    private String condition;

    private String action;

    private String description;


}
