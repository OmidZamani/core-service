package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name="HubCategory")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_HUBCATEGORY")
public class HubCategory extends BaseEntity implements java.io.Serializable {

   @Id
   @Column(name="PK_HUBCATEGORY_ID", nullable=false, insertable=true, updatable=true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;


   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted=false;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   @NotNull
   private String name;

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;


    public String selectToString() {
      return this.name +Constants.separator +(this.getCode()!=null ?this.getCode():"");
   }
}
