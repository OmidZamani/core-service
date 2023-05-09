package com.boxi.PriceList.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="PriceList")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_PRICELIST")
public class PriceList
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_PRICELIST_ID", nullable=false, insertable=true, updatable=true)
   private Long id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="PRICELISTCODE", insertable=true, updatable=true, length=254)
   private String priceListCode;

   @Basic(optional=true)
   @Column(name="PRICELISTNAME", insertable=true, updatable=true, length=254)
   private String priceListName;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="PRICELISTDATE", insertable=true, updatable=true)
   private Date priceListDate;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATEFROM", insertable=true, updatable=true)
   private Date validDateFrom;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATETO", insertable=true, updatable=true)
   private Date validDateTo;
   
   @OneToMany(fetch=FetchType.LAZY, mappedBy="priceList")
   private List<PriceListDetail> priceListDetails;
   
   
   
   public void addPriceListDetails(PriceListDetail newPriceListDetail) {
      if (newPriceListDetail == null)
         return;
      if (this.priceListDetails == null)
         this.priceListDetails = new ArrayList<PriceListDetail>();
      if (!this.priceListDetails.contains(newPriceListDetail))
      {
         this.priceListDetails.add(newPriceListDetail);
         newPriceListDetail.setPriceList(this);
      }
   }
   
   
   public void removePriceListDetails(PriceListDetail oldPriceListDetail) {
      if (oldPriceListDetail == null)
         return;
      if (this.priceListDetails != null)
         if (this.priceListDetails.contains(oldPriceListDetail))
         {
            this.priceListDetails.remove(oldPriceListDetail);
            oldPriceListDetail.setPriceList((PriceList)null);
         }
   }
   
   
//   public void removeAllPriceListDetails() {
//      if (priceListDetails != null)
//      {
//         PriceListDetail oldPriceListDetail;
//         for (Iterator iter = getIteratorPriceListDetails(); iter.hasNext();)
//         {
//            oldPriceListDetail = (PriceListDetail)iter.next();
//            iter.remove();
//            oldPriceListDetail.setPriceList((PriceList)null);
//         }
//      }
//   }


}
