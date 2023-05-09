/***********************************************************************
 * Module:  Hub.java
 * Author:  Gholaminezhad
 * Purpose: Defines the Class Hub
 ***********************************************************************/

package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.enums.HubType;
import com.boxi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import javax.persistence.*;


@Entity(name="Hub")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_HUB")
@Slf4j
@ToString
public class Hub extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PK_HUB_ID", nullable = false, insertable = true, updatable = true)
   private Long id;

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
   private HubType type;

   @Basic(optional=true)
   @Column(name="ACTIVEFLAG", insertable=true, updatable=true)
   private Boolean activeFlag;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="LOCATIONSTARTDATE", insertable=true, updatable=true)
   private Date locationStartDate;

   @Basic(optional=true)
   @Column(name="LOCLATE", insertable=true, updatable=true)
   private Double locLate;

   @Basic(optional=true)
   @Column(name="LOCLONG", insertable=true, updatable=true)
   private Double locLong;

   @Basic(optional=true)
   @Column(name="ADDRESSLINE", insertable=true, updatable=true, length=254)
   private String addressLine;

   @Basic(optional=true)
   @Column(name="ADDRESSDETAIL", insertable=true, updatable=true, length=254)
   private String addressDetail;


   @Basic(optional=true)
   @Column(name="ISPICKUPPOSSIBLE", insertable=true, updatable=true)
   private Boolean isPickupPossible;

   @Basic(optional=true)
   @Column(name="ISPOSSIBLECONSIGNMENTSTORAGE", insertable=true, updatable=true)
   private Boolean isPossibleConsignmentStorage;

   @Basic(optional=true)
   @Column(name="ISDELIVERYPOSSIBLE", insertable=true, updatable=true)
   private Boolean isDeliveryPossible;

   @Basic(optional=true)
   @Column(name="ISPOSSIBLEORDERREGISTRATION", insertable=true, updatable=true)
   private Boolean isPossibleOrderRegistration;

   @Basic(optional=true)
   @Column(name="ISARRIVALSCANPOSSIBLE", insertable=true, updatable=true)
   private Boolean isArrivalScanPossible;

   @Basic(optional=true)
   @Column(name="MANAGERID", insertable=true, updatable=true)
   private Long managerId;

   @OneToMany(fetch=FetchType.LAZY, mappedBy="parentHub")
   private List<Hub> hubs;

//   @OneToMany(fetch=FetchType.LAZY, mappedBy="hub") //برای فاز 2
//   private java.util.List<Shelf> shelfs;
//
//   @OneToMany(fetch=FetchType.LAZY) //برای فاز 2
//   private java.util.List<Pincode> pincodes;
//
//    @OneToMany(fetch=FetchType.LAZY, mappedBy="parentHub")
//    private java.util.List<Label> labels;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_HUBCATEGORY_ID", referencedColumnName="PK_HUBCATEGORY_ID", nullable=false)
   private HubCategory hubCategory;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PARENTHUB_ID", referencedColumnName="PK_HUB_ID", nullable=true)
   private Hub parentHub;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_CITY_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=true)
   private CountryDevision city;



   public void addHubs(Hub newHub) {
      if (newHub == null)
         return;
      if (this.hubs == null)
         this.hubs = new java.util.ArrayList<Hub>();
      if (!this.hubs.contains(newHub))
      {
         this.hubs.add(newHub);
         newHub.setParentHub(this);
      }
   }


   public void removeHubs(Hub oldHub) {
      if (oldHub == null)
         return;
      if (this.hubs != null)
         if (this.hubs.contains(oldHub))
         {
            this.hubs.remove(oldHub);
            oldHub.setParentHub((Hub)null);
         }
   }


   public void removeAllHubs() {
      if (hubs != null)
      {
         Hub oldHub;
         for (java.util.Iterator iter = getIteratorHubs(); iter.hasNext();)
         {
            oldHub = (Hub)iter.next();
            iter.remove();
            oldHub.setParentHub((Hub)null);
         }
      }
   }

   private Iterator getIteratorHubs() {
      return null;
   }

//   public void addShelfs(Shelf newShelf) {
//      if (newShelf == null)
//         return;
//      if (this.shelfs == null)
//         this.shelfs = new java.util.ArrayList<Shelf>();
//      if (!this.shelfs.contains(newShelf))
//      {
//         this.shelfs.add(newShelf);
//         newShelf.setHub(this);
//      }
//   }
//
//
//   public void removeShelfs(Shelf oldShelf) {
//      if (oldShelf == null)
//         return;
//      if (this.shelfs != null)
//         if (this.shelfs.contains(oldShelf))
//         {
//            this.shelfs.remove(oldShelf);
//            oldShelf.setHub((Hub)null);
//         }
//   }
//
//
//   public void removeAllShelfs() {
//      if (shelfs != null)
//      {
//         Shelf oldShelf;
//         for (java.util.Iterator iter = getIteratorShelfs(); iter.hasNext();)
//         {
//            oldShelf = (Shelf)iter.next();
//            iter.remove();
//            oldShelf.setHub((Hub)null);
//         }
//      }
//   }
//
//   public void addPincodes(Pincode newPincode) {
//      if (newPincode == null)
//         return;
//      if (this.pincodes == null)
//         this.pincodes = new java.util.ArrayList<Pincode>();
//      if (!this.pincodes.contains(newPincode))
//         this.pincodes.add(newPincode);
//   }
//
//
//   public void removePincodes(Pincode oldPincode) {
//      if (oldPincode == null)
//         return;
//      if (this.pincodes != null)
//         if (this.pincodes.contains(oldPincode))
//            this.pincodes.remove(oldPincode);
//   }
//
//
//   public void removeAllPincodes() {
//      if (pincodes != null)
//         pincodes.clear();
//   }
//
//   public void addLabels(Label newLabel) {
//      if (newLabel == null)
//         return;
//      if (this.labels == null)
//         this.labels = new java.util.ArrayList<Label>();
//      if (!this.labels.contains(newLabel))
//      {
//         this.labels.add(newLabel);
//         newLabel.setHub(this);
//      }
//   }
//
//
//   public void removeLabels(Label oldLabel) {
//      if (oldLabel == null)
//         return;
//      if (this.labels != null)
//         if (this.labels.contains(oldLabel))
//         {
//            this.labels.remove(oldLabel);
//            oldLabel.setHub((Hub)null);
//         }
//   }
//
//
//   public void removeAllLabels() {
//      if (labels != null)
//      {
//         Label oldLabel;
//         for (java.util.Iterator iter = getIteratorLabels(); iter.hasNext();)
//         {
//            oldLabel = (Label)iter.next();
//            iter.remove();
//            oldLabel.setHub((Hub)null);
//         }
//      }
//   }

   /**
    * Empty constructor which is required by EJB 3.0 spec.
    *
    */
   public Hub() {
      // TODO Add your own initialization code here.
   }

   public String selectToString() {
//     return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
      return this.getName();
   }

}

