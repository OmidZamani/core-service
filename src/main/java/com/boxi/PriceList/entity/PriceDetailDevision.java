/***********************************************************************
 * Module:  PriceDetailDevision.java
 * Author:  YAS
 * Purpose: Defines the Class PriceDetailDevision
 ***********************************************************************/

package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.CountryDevision;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name="PriceDetailDevision")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_PRICEDETAILDEVISION")
public class PriceDetailDevision
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_PRICEDETAILDEVISION_ID", nullable=false, insertable=true, updatable=true)
   private Long id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRICELISTDETAIL_ID", referencedColumnName="PK_PRICELISTDETAIL_ID", nullable=true)
   private PriceListDetail priceListDetail;
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_FROMCOUNTRYDEVISION_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=true)
   private CountryDevision fromCountryDevision;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_TOCOUNTRYDEVISION_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=true)
   private CountryDevision toCountryDevision;
   

}
