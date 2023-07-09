package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Entity(name = "TypesOfPackage")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_TYPESOFPACKAGE")
public class TypesOfPackage
        extends BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TYPESOFPACKAGE_ID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted;

    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, insertable = true, updatable = true, length = 254)
    private String code;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 254)
    private String name;

    @Basic(optional = true)
    @Column(name = "DESCRIPTION", insertable = true, updatable = true, length = 254)
    private String description;

    @Basic(optional = true)
    @Column(name = "VOLUME", insertable = true, updatable = true)
    private Double volume;

    @Basic(optional = false)
    @Column(name = "LENGTH", nullable = false, insertable = true, updatable = true)
    private Double length;

    @Basic(optional = false)
    @Column(name = "WIDTH", nullable = false, insertable = true, updatable = true)
    private Double width;

    @Basic(optional = false)
    @Column(name = "HEIGHT", nullable = false, insertable = true, updatable = true)
    private Double height;

    @Basic(optional = true)
    @Column(name = "WEIGHT", insertable = true, updatable = true)
    private Double weight;

    @Basic(optional = true)
    @Column(name = "MAXACCEPTABLEWEIGHT", insertable = true, updatable = true)
    private Double maxAcceptableWeight;

    @Basic(optional = true)
    @Column(name = "PREDEFINEDACCEPTABLEWEIGHT", insertable = true, updatable = true)
    private Double predefinedAcceptableWeight;


    public TypesOfPackage() {
        // TODO Add your own initialization code here.
    }

}
