package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity(name="DeliveryDiscount")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_DELIVERYDISCOUNT")
public class DeliveryDiscount
        extends BaseEntity
        implements java.io.Serializable {

   // چیزهایی که این تو می افتند بصورت پویا با توجه به اردر
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_DELIVERYDISCOUNT_ID", nullable=false, insertable=true, updatable=false)
   private Long Id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true, length=1)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true, length=1)
   private Boolean isDeleted;

   @Basic(optional=false)
   @Column(name="TYPE", nullable=false, insertable=true, updatable=true)
   private Long type;

   @Basic(optional=false)
   @Column(name="DISCOUNTFROM", nullable=false, insertable=true, updatable=true)
   private BigDecimal discountFrom;

   @Basic(optional=true)
   @Column(name="DISCOUNTTO", insertable=true, updatable=true)
   private BigDecimal discountTo;

   @Basic(optional=false)
   @Column(name="DISCOUNTPERCENT", nullable=false, insertable=true, updatable=true)
   private BigDecimal discountPercent;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SERVICEDELIVERY_ID", referencedColumnName="PK_SERVICEDELIVERY_ID", nullable=true)
   private ServiceDelivery serviceDelivery;
   

   public DeliveryDiscount() {
      // TODO Add your own initialization code here.
   }

}
