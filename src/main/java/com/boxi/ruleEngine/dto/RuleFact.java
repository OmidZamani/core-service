package com.boxi.ruleEngine.dto;

import lombok.Data;

@Data
public class RuleFact {



    private Integer w=0; //وزن

    private String cdt="-"; //,برون استانی غیرهمجوار, درون استانی است ,برون استانی همجوار

    private String cType="-"; // ENVELOPE("پاکت", 0), PACKAGE("بسته", 1), POSTALCARD("کارت پستال", 2);

    private String p="-"; //available name of product as service
}
