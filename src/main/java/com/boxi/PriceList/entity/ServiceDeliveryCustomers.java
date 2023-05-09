package com.boxi.PriceList.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="ServiceDeliveryCustomers")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_SERVICEDELIVERYCUSTOMERS")
public class ServiceDeliveryCustomers implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)   
   @Column(name="PK_SERVICEDELIVERYCUSTOMERS_ID", nullable=false, insertable=true, updatable=false)
   private Long Id;

   @Basic(optional=false)
   @Column(name="CUSTOMERID", nullable=false, insertable=true, updatable=true)
   private Long customerId;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SERVICEDELIVERY_ID", referencedColumnName="PK_SERVICEDELIVERY_ID", nullable=false)
   private ServiceDelivery serviceDelivery;
   
}
