package com.boxi.crm.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.*;
import javax.persistence.*;

@Entity(name="CustomerSegment")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_CUSTOMERSEGMENT")
public class CustomerSegment
        extends BaseEntity
        implements java.io.Serializable {
   //گروه مشتری ux
   //یا یک سری مشتری رو گروه میکنن یا یک  شرطی میزارن که کلی مشتری رو شامل میشود مثلا مشتری های خراسان

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_CUSTOMERSEGMENT_ID", nullable=false, insertable=true, updatable=false)
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

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;

   @Basic(optional=true)
   @Column(name="CUSTOMERSEGMENTATIONRULE", insertable=true, updatable=true, length=254)
   private String customerSegmentationRule; // rule engine text area
   
   @OneToMany(fetch=FetchType.LAZY, mappedBy="segment")
   private List<SegmentCustomers> segmentCustomers;
   
   
   
   public void addSegmentCustomers(SegmentCustomers newSegmentCustomers) {
      if (newSegmentCustomers == null)
         return;
      if (this.segmentCustomers == null)
         this.segmentCustomers = new ArrayList<SegmentCustomers>();
      if (!this.segmentCustomers.contains(newSegmentCustomers))
      {
         this.segmentCustomers.add(newSegmentCustomers);
         newSegmentCustomers.setSegment(this);
      }
   }
   
   
   public void removeSegmentCustomers(SegmentCustomers oldSegmentCustomers) {
      if (oldSegmentCustomers == null)
         return;
      if (this.segmentCustomers != null)
         if (this.segmentCustomers.contains(oldSegmentCustomers))
         {
            this.segmentCustomers.remove(oldSegmentCustomers);
            oldSegmentCustomers.setSegment((CustomerSegment)null);
         }
   }
   
   
   public void removeAllSegmentCustomers() {
      if (segmentCustomers != null)
      {
         SegmentCustomers oldSegmentCustomers;
         for (Iterator iter = getIteratorSegmentCustomers(); iter.hasNext();)
         {
            oldSegmentCustomers = (SegmentCustomers)iter.next();
            iter.remove();
            oldSegmentCustomers.setSegment((CustomerSegment)null);
         }
      }
   }

   private Iterator getIteratorSegmentCustomers() {
      return null;
   }

   public CustomerSegment() {
      // TODO Add your own initialization code here.
   }

}
