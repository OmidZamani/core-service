package com.boxi.crm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="SegmentCustomers")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_SEGMENTCUSTOMERS")
public class SegmentCustomers implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_SEGMENTCUSTOMERS_ID", nullable=false, insertable=true, updatable=false)
   private Long Id;

   @Basic(optional=true)
   @Column(name="CUSTOMERID", insertable=true, updatable=true)
   private Long customerId;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SEGMENT_ID", referencedColumnName="PK_CUSTOMERSEGMENT_ID", nullable=false)
   private CustomerSegment segment;
   
   public SegmentCustomers() {
      // TODO Add your own initialization code here.
   }

}
