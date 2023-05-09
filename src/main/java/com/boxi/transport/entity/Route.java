/***********************************************************************
 * Module:  Route.java
 * Author:  Gholaminezhad
 * Purpose: Defines the Class Route
 ***********************************************************************/

package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;


@Entity(name="Route")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_ROUTE")
public class Route extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_ROUTE_ID", nullable=false, insertable=true, updatable=true, length=254)
   private Long id;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SOURCE_HUB_ID", referencedColumnName="PK_HUB_ID", nullable=false)
   private Hub sourceHub;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_TARGET_HUB_ID", referencedColumnName="PK_HUB_ID", nullable=false)
   private Hub targetHub;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=false)
   @Column(name="NODES", nullable=false, insertable=true, updatable=true)
   private Long nodes;

   @Basic(optional=true)
   @Column(name="DISTANCE", insertable=true, updatable=true)
   private Double distance;

   @Basic(optional=true)
   @Column(name="DISTANCEVARIANCE", insertable=true, updatable=true)
   private Double distanceVariance;

   @Basic(optional=true)
   @Column(name="TRANSITTIME", insertable=true, updatable=true)
   private Double transitTime;

   @Basic(optional=true)
   @Column(name="TIMESTOPPAGE", insertable=true, updatable=true)
   private Double timeStoppage;
   
   @OneToMany(fetch=FetchType.LAZY, mappedBy="route")
   private List<Connection> connections;
   
   
   public void addConnection(Connection newConnection) {
      if (newConnection == null)
         return;
      if (this.connections == null)
         this.connections = new ArrayList<Connection>();
      if (!this.connections.contains(newConnection))
      {
         this.connections.add(newConnection);
         newConnection.setRoute(this);
      }
   }


   
   
   public void removeConnections(Connection oldConnection) {
      if (oldConnection == null)
         return;
      if (this.connections != null)
         if (this.connections.contains(oldConnection))
         {
            this.connections.remove(oldConnection);
            oldConnection.setRoute((Route)null);
         }
   }


   public String selectToString() {
      return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
   }
}
