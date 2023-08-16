/***********************************************************************
 * Module:  Connection.java
 * Author:  Gholaminezhad
 * Purpose: Defines the Class Connection
 ***********************************************************************/

package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity(name="Connection")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_CONNECTION")
public class Connection extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_CONNECTION_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="CODE", insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=true)
   @Column(name="DISTANCEFROMPREVIOUSHUB", insertable=true, updatable=true)
   private Double distanceFromPreviousHub;

   @Basic(optional=true)
   @Column(name="DISTANCEVARIANCE", insertable=true, updatable=true)
   private Double distanceVariance;

   @Basic(optional=true)
   @Column(name="TRANSITTIME", insertable=true, updatable=true)
   private Double transitTime;

   @Basic(optional=true)
   @Column(name="TIMESTOPPAGE", insertable=true, updatable=true)
   private Double timeStoppage;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_ROUTE_ID", referencedColumnName="PK_ROUTE_ID", nullable=true)
   private Route route;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_HUB_ID", referencedColumnName="PK_HUB_ID", nullable=true)
   private Hub hub;

   @Basic(optional=true)
   @Column(name="PRIORITY", insertable=true, updatable=true)
   private Short priority; // node numbers

   public String selectToString() {
      return id+"";
   }

/*
   @ManyToOne(fetch=FetchType.LAZY)
   private Customer customer;*/ //TODO


}
