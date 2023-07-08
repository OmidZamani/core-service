package com.boxi.ruleEngine.dto;

import lombok.Data;
import lombok.ToString;

@ToString
public class RuleFact {



    private Double w=0d; //وزن

    private String cdt="-"; //برون استانی غیر هم جوار, درون استانی است ,برون استانی همجوار

    private String cd; //استان countryDevision.title

    private Double t=0d; //مدت ارایه خدمت // to -from Timecomitment

    private Double at=0d; // زمان قبول مرسوله 00:00 24:00 from

    private String cct; //contentType   DOCUMENT("اسناد و مدارک", 0), NON_DOCUMENT("غیر اسناد و مدارک", 1), NEWS_PAPER("مطبوع", 2);

    private String cot="-"; //consignmentType ENVELOPE("پاکت", 0), PACKAGE("بسته", 1), POSTALCARD("کارت پستال", 2);

    private String v; //ارزش مرسوله // consignment.value

    private Integer np; //تعداد مرسولات سفارش numberOfPiece


    //...........

    private String service="-"; //available name of product as service

    //...........
    private Integer price=0;


    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public String getCdt() {
        return cdt;
    }

    public void setCdt(String cdt) {
        this.cdt = cdt;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getAt() {
        return at;
    }

    public void setAt(Double at) {
        this.at = at;
    }

    public String getCct() {
        return cct;
    }

    public void setCct(String cct) {
        this.cct = cct;
    }

    public String getCot() {
        return cot;
    }

    public void setCot(String cot) {
        this.cot = cot;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Integer getNp() {
        return np;
    }

    public void setNp(Integer np) {
        this.np = np;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
