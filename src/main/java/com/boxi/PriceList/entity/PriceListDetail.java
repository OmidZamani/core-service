package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.product.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity(name="PriceListDetail")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_PRICELISTDETAIL")
public class PriceListDetail
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_PRICELISTDETAIL_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="PRICE", insertable=true, updatable=true)
   private BigDecimal price;

   @Basic(optional=true)
   @Column(name="PRICEFORMULE", insertable=true, updatable=true, length=254)
   private String priceFormule; //text area rule engine

   @Basic(optional=true)
   @Column(name="FROMWEIGHT", insertable=true, updatable=true)
   private Double fromWeight;

   @Basic(optional=true)
   @Column(name="TOWEIGHT", insertable=true, updatable=true)
   private Double toWeight;

   @Basic(optional=true)
   @Column(name="FROMDIM", insertable=true, updatable=true)
   private Double fromDim;

   @Basic(optional=true)
   @Column(name="TODIMENSION", insertable=true, updatable=true)
   private Double toDimension;

   @Basic(optional=true)
   @Column(name="FROMVALUE", insertable=true, updatable=true)
   private BigDecimal fromValue;

   @Basic(optional=true)
   @Column(name="TOVALUE", insertable=true, updatable=true)
   private BigDecimal toValue;

   @Basic(optional=true)
   @Column(name="FROMNUMBER", insertable=true, updatable=true)
   private Long fromNumber;

   @Basic(optional=true)
   @Column(name="TONUMBER", insertable=true, updatable=true)
   private Long toNumber;

   @Basic(optional=false)
   @Column(name="ISPARAMETRIC", nullable=false, insertable=true, updatable=true, length=1)
   private Boolean isParametric;  // when has formula

   @OneToMany(fetch=FetchType.LAZY, mappedBy="priceListDetail")
   private List<PriceDetailDevision> priceDetailDevisions;

   @ManyToOne(fetch=FetchType.LAZY)
   private CustomCountryDevision customCountryDevision; //  همجوار

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRODUCT_ID", referencedColumnName="PK_PRODUCT_ID", nullable=false)
   private Product product;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRICELIST_ID", referencedColumnName="PK_PRICELIST_ID", nullable=false)
   private PriceList priceList;

   @Basic(optional=true)
   @Column(name="CONSIGNMENTTYPE", insertable=true, updatable=true)
   private Long consignmentType;
   
   public void addPriceDetailDevisions(PriceDetailDevision newPriceDetailDevision) {
      if (newPriceDetailDevision == null)
         return;
      if (this.priceDetailDevisions == null)
         this.priceDetailDevisions = new Vector<PriceDetailDevision>();
      if (!this.priceDetailDevisions.contains(newPriceDetailDevision))
      {
         this.priceDetailDevisions.add(newPriceDetailDevision);
         newPriceDetailDevision.setPriceListDetail(this);
      }
   }
   
   
   public void removePriceDetailDevisions(PriceDetailDevision oldPriceDetailDevision) {
      if (oldPriceDetailDevision == null)
         return;
      if (this.priceDetailDevisions != null)
         if (this.priceDetailDevisions.contains(oldPriceDetailDevision))
         {
            this.priceDetailDevisions.remove(oldPriceDetailDevision);
            oldPriceDetailDevision.setPriceListDetail((PriceListDetail)null);
         }
   }
   
   
   public void removeAllPriceDetailDevisions() {
      if (priceDetailDevisions != null)
      {
         PriceDetailDevision oldPriceDetailDevision;
         for (Iterator iter = getIteratorPriceDetailDevisions(); iter.hasNext();)
         {
            oldPriceDetailDevision = (PriceDetailDevision)iter.next();
            iter.remove();
            oldPriceDetailDevision.setPriceListDetail((PriceListDetail)null);
         }
      }
   }

   private Iterator getIteratorPriceDetailDevisions() {
      return null;
   }

   /**
    * Empty constructor which is required by EJB 3.0 spec.
    *
    */
   public PriceListDetail() {
      // TODO Add your own initialization code here.
   }

}
