package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.product.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="Service")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_SERVICE")
public class Services
        extends BaseEntity
        implements java.io.Serializable {

   /* */

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_SERVICE_ID", nullable=false, insertable=true, updatable=false)
   private Long Id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true, length=1)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true, length=1)
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
   //پایه و جانبی

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description; //

   @Basic(optional=true)
   @Column(name="MINIMUMORDERQUANTITY", insertable=true, updatable=true)
   private Long minimumOrderQuantity;

   @Basic(optional=false)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATEFROM", nullable=false, insertable=true, updatable=true)
   private Date validDateFrom;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATETO", insertable=true, updatable=true)
   private Date validDateTo;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRODUCT_ID", referencedColumnName="PK_PRODUCT_ID", nullable=false)
   private Product product;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRICELIST_ID", referencedColumnName="PK_PRICELIST_ID", nullable=false)
   private PriceList priceList;



}
