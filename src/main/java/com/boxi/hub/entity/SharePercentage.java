package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Entity(name="SharePercentage")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_SHAREPERCENTAGE")
public class SharePercentage
        extends BaseEntity
        implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_SHAREPERCENTAGE_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="PRICEFROM", insertable=true, updatable=true)
   private BigDecimal priceFrom;

   @Basic(optional=true)
   @Column(name="PRICETO", insertable=true, updatable=true)
   private BigDecimal priceTo;

   @Basic(optional=true)
   @Column(name="AMOUNT", insertable=true, updatable=true)
   private BigDecimal amount;

   @Basic(optional=true)
   @Column(name="VERSION", insertable=true, updatable=true)
   private Short version;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDFROM", insertable=true, updatable=true)
   private Date validFrom;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="VALIDTO", insertable=true, updatable=true)
   private Date validTo;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_BANKACCOUNT_ID", referencedColumnName="PK_BANKACCOUNT_ID", nullable=true)
   private BankAccount bankAccount;
   

   public SharePercentage() {
      // TODO Add your own initialization code here.
   }

}
