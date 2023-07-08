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

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @Lob
    @NotBlank
    private String condition;

    @Column(nullable = false)
    @Lob
    private String content;

    @NotBlank
    private String action;

    private String description;



}
