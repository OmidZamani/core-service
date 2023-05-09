package com.boxi.hub.payload.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ClobEntityDto {


    // other columns

    public String getP_polygon() {
        return p_polygon;
    }

    public void setP_polygon(String p_polygon) {
        this.p_polygon = p_polygon;
    }

    @Column(name = "p_polygon", columnDefinition = "CLOB NOT NULL")
    @Lob
    private String p_polygon;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // setters and getters

}
