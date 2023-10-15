/***********************************************************************
 * Module:  CountryDevision.java
 * Author:  Gholaminezhad
 * Purpose: Defines the Class CountryDevision
 ***********************************************************************/

package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.enums.ContryTypes;
import com.boxi.hub.enums.CountryType;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;


@Entity(name="CountryDevision")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_COUNTRYDEVISION")
@ToString
public class CountryDevision extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_COUNTRYDEVISION_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=false)
   @Column(name="TYPE", nullable=false, insertable=true, updatable=true)
   @Enumerated
   private CountryType countryType;

   @Basic(optional=true)
   @Column(name="LONGTITUDE", insertable=true, updatable=true)
   private Double longtitude;

   @Basic(optional=true)
   @Column(name="LATITUDE", insertable=true, updatable=true)
   private Double latitude;

   @Basic(optional=true)
   @Column(name="RESPONSIBLEPERSONELID", insertable=true, updatable=true)
   private Long responsiblePersonelId;

   @Basic(optional=true)
   @Column(name="HUBID", insertable=true, updatable=true)
   private Long hubId;

   @JsonIgnore
   @OneToMany(fetch=FetchType.LAZY, mappedBy="parent")
   private List<CountryDevision> childs;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PARENT_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=false)
   private CountryDevision parent;

   @Basic(optional=true)
   @Column(name="SHAHRCODE", insertable=true, updatable=true, length=254)
   private String shahrCode;

   @Basic(optional=true)
   @Column(name="SHAHRESTANCODE", insertable=true, updatable=true, length=254)
   private String shahrestanCode;

   @Basic(optional=true)
   @Column(name="OSTANCODE", insertable=true, updatable=true, length=254)
   private String ostanCode;

   public String selectToString() {
      return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
   }
   public void addChilds(CountryDevision newCountryDevision) {
      if (newCountryDevision == null)
         return;
      if (this.childs == null)
         this.childs = new java.util.ArrayList<CountryDevision>();
      if (!this.childs.contains(newCountryDevision))
      {
         this.childs.add(newCountryDevision);
         newCountryDevision.setParent(this);
      }
   }


   public void removeChilds(CountryDevision oldCountryDevision) {
      if (oldCountryDevision == null)
         return;
      if (this.childs != null)
         if (this.childs.contains(oldCountryDevision))
         {
            this.childs.remove(oldCountryDevision);
            oldCountryDevision.setParent((CountryDevision)null);
         }
   }


   public void removeAllChilds() {
      if (childs != null)
      {
         CountryDevision oldCountryDevision;
         for (java.util.Iterator iter = getIteratorChilds(); iter.hasNext();)
         {
            oldCountryDevision = (CountryDevision)iter.next();
            iter.remove();
            oldCountryDevision.setParent((CountryDevision)null);
         }
      }
   }

   private Iterator getIteratorChilds() {
      return null;
   }

   /**
    * Empty constructor which is required by EJB 3.0 spec.
    *
    */
   public CountryDevision() {
      // TODO Add your own initialization code here.
   }


}
