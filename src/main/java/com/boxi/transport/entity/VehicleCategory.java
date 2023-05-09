/***********************************************************************
 * Module:  VehicleCategory.java
 * Author:  Gholaminezhad
 * Purpose: Defines the Class VehicleCategory
 ***********************************************************************/

package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name="VehicleCategory")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_VEHICLECATEGORY")
public class VehicleCategory extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_VEHICLECATEGORY_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", insertable=true, updatable=true)
   private Boolean isActive=true;

   @Basic(optional=false)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted=false;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   public String selectToString() {
      return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
   }

}
