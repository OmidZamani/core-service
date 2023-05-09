/***********************************************************************
 * Module:  Exception.java
 * Author:  YAS
 * Purpose: Defines the Class Exception
 ***********************************************************************/

package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name="Exception")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_EXCEPTION")
public class Exception
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_EXCEPTION_ID", insertable=true, updatable=false)
   private Long Id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=false)
   @Column(name="TYPE", nullable=false, insertable=true, updatable=true)
   private Long type;

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;
   

   public Exception() {
      // TODO Add your own initialization code here.
   }

}
