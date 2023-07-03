package com.boxi.ruleEngine.dto;

import lombok.Data;

@Data
public class RuleFact {



    private Double w=0d; //وزن

    private String cdt="-"; //برون استانی غیر هم جوار, درون استانی است ,برون استانی همجوار

    private Integer t; //مدت ارایه خدمت // Timecomitment

    private String cct; //contentType   DOCUMENT("اسناد و مدارک", 0), NON_DOCUMENT("غیر اسناد و مدارک", 1), NEWS_PAPER("مطبوع", 2);

    private String cot="-"; //consignmentType ENVELOPE("پاکت", 0), PACKAGE("بسته", 1), POSTALCARD("کارت پستال", 2);

    private String v; //ارزش مرسوله // consignment.value

    private Integer np; //تعداد مرسولات سفارش numberOfPiece

    private String countryDevision; //استان countryDevision.title

    private long acceptTime; // زمان قبول مرسوله 00:00 24:00


    //...........

    private String service="-"; //available name of product as service


}
