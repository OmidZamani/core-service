package com.boxi.PriceList.entity;

import com.boxi.PriceList.Enum.DeliveryServiceType;
import com.boxi.core.entity.BaseEntity;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SalesChannel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity(name="ServiceDelivery")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_SERVICEDELIVERY")
public class ServiceDelivery
        extends BaseEntity
        implements java.io.Serializable {

   /**/

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_SERVICEDELIVERY_ID", nullable=false, insertable=true, updatable=false)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true, length=1)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true, length=1)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="CODE", insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=true)
   @Column(name="NAME", insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=true)
   @Column(name="TYPE", insertable=true, updatable=true)
   @Enumerated
   private DeliveryServiceType serviceType;
   //استاتیک و دینامیک

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATEFROM", insertable=true, updatable=true)
   private Date validDateFrom;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDDATETO", insertable=true, updatable=true)
   private Date validDateTo;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SERVICE_ID", referencedColumnName="PK_SERVICE_ID", nullable=false)
   private Services service;

   @ManyToMany(fetch=FetchType.LAZY)
   @JoinTable(
      name="TBL_SERVICEDELIVERYCHANNELS",
      joinColumns={
         @JoinColumn(name="FK_SERVICE_DELIVERIES_ID", referencedColumnName="PK_SERVICEDELIVERY_ID", nullable=false)
      },
      inverseJoinColumns={
         @JoinColumn(name="FK_SALES_CHANNELS_ID", referencedColumnName="PK_SALESCHANNEL_ID", nullable=false)
      }
   )
   private List<SalesChannel> saleschannels;

   @ManyToMany(fetch=FetchType.LAZY)
   @JoinTable(
      name="TBL_SERVICEDELIVERYSEGMENTS",
      joinColumns={
         @JoinColumn(name="FK_SERVICEDELIVERIES_ID", referencedColumnName="PK_SERVICEDELIVERY_ID", nullable=false)
      },
      inverseJoinColumns={
         @JoinColumn(name="FK_CUSTOMERSEGMENTS_ID", referencedColumnName="PK_CUSTOMERSEGMENT_ID", nullable=false)
      }
   )
   private List<CustomerSegment> customerSegments;

   @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceDelivery")
   private List<ServiceDeliveryCustomers> serviceDeliveryCustomers;


   @OneToMany(fetch=FetchType.LAZY, mappedBy="serviceDelivery")
   private List<DeliveryDiscount> deliveryDiscounts;

   @Basic(optional=false)
   @Column(name="DISCOUNTPERCENT", nullable=false, insertable=true, updatable=true)
   private BigDecimal discountPercent;

   public void addDeliveryDiscounts(DeliveryDiscount newDeliveryDiscount) {
      if (newDeliveryDiscount == null)
         return;
      if (this.deliveryDiscounts == null)
         this.deliveryDiscounts = new java.util.ArrayList<DeliveryDiscount>();
      if (!this.deliveryDiscounts.contains(newDeliveryDiscount))
      {
         this.deliveryDiscounts.add(newDeliveryDiscount);
         newDeliveryDiscount.setServiceDelivery(this);
      }
   }


   public void removeDeliveryDiscounts(DeliveryDiscount oldDeliveryDiscount) {
      if (oldDeliveryDiscount == null)
         return;
      if (this.deliveryDiscounts != null)
         if (this.deliveryDiscounts.contains(oldDeliveryDiscount))
         {
            this.deliveryDiscounts.remove(oldDeliveryDiscount);
            oldDeliveryDiscount.setServiceDelivery((ServiceDelivery)null);
         }
   }


   public void removeAllDeliveryDiscounts() {
      if (deliveryDiscounts != null)
      {
         DeliveryDiscount oldDeliveryDiscount;
         for (java.util.Iterator iter = getIteratorDeliveryDiscounts(); iter.hasNext();)
         {
            oldDeliveryDiscount = (DeliveryDiscount)iter.next();
            iter.remove();
            oldDeliveryDiscount.setServiceDelivery((ServiceDelivery)null);
         }
      }
   }

   private Iterator getIteratorDeliveryDiscounts() {
      return null;
   }

   public void addSaleschannels(SalesChannel newSalesChannel) {
      if (newSalesChannel == null)
         return;
      if (this.saleschannels == null)
         this.saleschannels = new ArrayList<SalesChannel>();
      if (!this.saleschannels.contains(newSalesChannel))
         this.saleschannels.add(newSalesChannel);
   }
   
   
   public void removeSaleschannels(SalesChannel oldSalesChannel) {
      if (oldSalesChannel == null)
         return;
      if (this.saleschannels != null)
         if (this.saleschannels.contains(oldSalesChannel))
            this.saleschannels.remove(oldSalesChannel);
   }
   
   
   public void removeAllSaleschannels() {
      if (saleschannels != null)
         saleschannels.clear();
   }
   
   public void addCustomerSegments(CustomerSegment newCustomerSegment) {
      if (newCustomerSegment == null)
         return;
      if (this.customerSegments == null)
         this.customerSegments = new ArrayList<CustomerSegment>();
      if (!this.customerSegments.contains(newCustomerSegment))
         this.customerSegments.add(newCustomerSegment);
   }
   
   
   public void removeCustomerSegments(CustomerSegment oldCustomerSegment) {
      if (oldCustomerSegment == null)
         return;
      if (this.customerSegments != null)
         if (this.customerSegments.contains(oldCustomerSegment))
            this.customerSegments.remove(oldCustomerSegment);
   }
   
   
   public void removeAllCustomerSegments() {
      if (customerSegments != null)
         customerSegments.clear();
   }
   
   public void addServiceDeliveryCustomers(ServiceDeliveryCustomers newServiceDeliveryCustomers) {
      if (newServiceDeliveryCustomers == null)
         return;
      if (this.serviceDeliveryCustomers == null)
         this.serviceDeliveryCustomers = new ArrayList<ServiceDeliveryCustomers>();
      if (!this.serviceDeliveryCustomers.contains(newServiceDeliveryCustomers))
      {
         this.serviceDeliveryCustomers.add(newServiceDeliveryCustomers);
         newServiceDeliveryCustomers.setServiceDelivery(this);
      }
   }
   
   
   public void removeServiceDeliveryCustomers(ServiceDeliveryCustomers oldServiceDeliveryCustomers) {
      if (oldServiceDeliveryCustomers == null)
         return;
      if (this.serviceDeliveryCustomers != null)
         if (this.serviceDeliveryCustomers.contains(oldServiceDeliveryCustomers))
         {
            this.serviceDeliveryCustomers.remove(oldServiceDeliveryCustomers);
            oldServiceDeliveryCustomers.setServiceDelivery((ServiceDelivery)null);
         }
   }
   
   
   public void removeAllServiceDeliveryCustomers() {
      if (serviceDeliveryCustomers != null)
      {
         ServiceDeliveryCustomers oldServiceDeliveryCustomers;
         for (Iterator iter = getIteratorServiceDeliveryCustomers(); iter.hasNext();)
         {
            oldServiceDeliveryCustomers = (ServiceDeliveryCustomers)iter.next();
            iter.remove();
            oldServiceDeliveryCustomers.setServiceDelivery((ServiceDelivery)null);
         }
      }
   }

   private Iterator getIteratorServiceDeliveryCustomers() {
      return null;
   }

   public ServiceDelivery() {
      // TODO Add your own initialization code here.
   }

}
