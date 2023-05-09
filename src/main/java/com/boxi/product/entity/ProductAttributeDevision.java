/***********************************************************************
 * Module:  ProductAttributeDevision.java
 * Author:  YAS
 * Purpose: Defines the Class ProductAttributeDevision
 ***********************************************************************/

package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.CountryDevision;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/** @pdOid 0736ed4a-d0bf-4f52-84de-a96087d95f56 */
@Entity(name="ProductAttributeDevision")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_PRODUCTATTRIBUTEDEVISION")
public class  ProductAttributeDevision extends BaseEntity
                                      implements java.io.Serializable    {

   /* برای چک محل PRODUCT*/
   @Id
   @Column(name="PK_PRODUCTATTDEVISION_ID", nullable=false, insertable=true, updatable=true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=false)
   @Column(name="ISDELETED", nullable=false, insertable=true, updatable=true)
   private Boolean isDeleted;
   
   @ManyToOne(fetch=FetchType.LAZY)
   private CountryDevision fromCountryDevision;

   @ManyToOne(fetch=FetchType.LAZY)
   private CountryDevision toCountryDevision;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRODUCTATTRIBUTE_ID", referencedColumnName="PK_PRODUCTATTRIBUTE_ID", nullable=true)
   private ProductAttribute productAttribute;
   


   public String selectToString() {
      return null;
   }
}

