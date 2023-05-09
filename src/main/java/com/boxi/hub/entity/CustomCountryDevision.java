/***********************************************************************
 * Module:  CustomCountryDevision.java
 * Author:  YAS
 * Purpose: Defines the Class CustomCountryDevision
 ***********************************************************************/

package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.*;
import javax.persistence.*;

@Entity(name = "CustomCountryDevision")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_CUSTOMCOUNTRYDEVISION")
public class CustomCountryDevision
        extends BaseEntity
        implements java.io.Serializable {
    //همجوار

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CUSTOMDEVISION_ID", insertable = true, updatable = true)
    private Long id;

    @Basic(optional = true)
    @Column(name = "CODE", insertable = true, updatable = true, length = 254)
    private String code;

    @Basic(optional = true)
    @Column(name = "NAME", insertable = true, updatable = true, length = 254)
    private String name;


    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customCountryDevision")
    private List<CustomDevisionDetail> customDevisionDetails;



}
