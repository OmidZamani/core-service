package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.persistence.*;
import com.boxi.PriceList.entity.Exception;

import java.io.Serializable;

@Entity(name="BagExceptions")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_BAGEXCEPTIONS")
public class BagExceptions
        extends BaseEntity
        implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_BAGEXCEPTIONS_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;
   
   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_EXCEPTION_ID", referencedColumnName="PK_EXCEPTION_ID", nullable=false)
   private Exception exception;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_BAG_ID", referencedColumnName="PK_BAG_ID", nullable=false)
   private Bag bag;
   
   public BagExceptions() {
      // TODO Add your own initialization code here.
   }

}
