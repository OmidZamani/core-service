/***********************************************************************
 * Module:  CustomDevisionDetail.java
 * Author:  YAS
 * Purpose: Defines the Class CustomDevisionDetail
 ***********************************************************************/

package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "CustomDevisionDetail")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_CUSTOMDEVISIONDETAIL")
public class CustomDevisionDetail
        extends BaseEntity
        implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CUSTOMDEVISIONDETAIL_ID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;//dont check

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CUSTOMDEVISION_ID", referencedColumnName = "PK_CUSTOMDEVISION_ID", nullable = false)
    private CustomCountryDevision customCountryDevision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TOCOUNTRYDEVISION_ID", referencedColumnName = "PK_COUNTRYDEVISION_ID", nullable = false)
    private CountryDevision toCountryDevision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_FROMCOUNTRYDEVISION_ID", referencedColumnName = "PK_COUNTRYDEVISION_ID", nullable = false)
    private CountryDevision fromCountryDevision;




}
