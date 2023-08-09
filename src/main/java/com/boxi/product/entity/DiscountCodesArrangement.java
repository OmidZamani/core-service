package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Entity(name="DiscountCodesArrangement")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_DISCOUNTCODESARRANGEMENT")
public class DiscountCodesArrangement
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_DISCOUNTCODESARRANGEMENT_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=true)
   @Column(name="PRIORITY", insertable=true, updatable=true)
   private Short priority;

   @Basic(optional=true)
   @Column(name="AMOUNT", insertable=true, updatable=true)
   private BigDecimal amount;

   @Basic(optional=true)
   @Column(name="PERCENT", insertable=true, updatable=true)
   private BigDecimal percent;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_DISCOUNTCODE_ID", referencedColumnName="PK_DISCOUNTCODE_ID", nullable=false)
   private DiscountCode discountCode;
   

   public DiscountCodesArrangement() {
      // TODO Add your own initialization code here.
   }

}
