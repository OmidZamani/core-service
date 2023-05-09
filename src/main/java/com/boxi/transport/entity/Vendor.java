
package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name="Vendor")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_VENDOR")
public class Vendor extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_VENDOR_ID", nullable=false, insertable=true, updatable=false)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive=true;

   @Basic(optional=false)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted=false;

   @Basic(optional=false)
   @Column(name="NAME", insertable=true, updatable=true, length=254)
   @NotNull
   private String name;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   @NotNull
   private String code;

   @Basic(optional=true)
   @Column(name="CONTACTNUMBER", insertable=true, updatable=true, length=254)
   private String contactNumber;

   @Basic(optional=false)
   @Column(name="NATIONAL_CODE", nullable=false, insertable=true, updatable=true, length=254)
   @NotNull
   private  String nationalCode;


    public String selectToString() {
       return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");

    }
}
