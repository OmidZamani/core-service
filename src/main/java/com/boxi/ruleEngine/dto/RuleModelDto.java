package com.boxi.ruleEngine.dto;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class RuleModelDto {

    private int priority;

    private String name;

    private String code;

    private String condition;

    private String action;

    private String description;
}
