package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.persistence.*;
import com.boxi.PriceList.entity.Exception;

import java.io.Serializable;


@Entity(name="VehicleExceptions")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_VEHICLEEXCEPTIONS")
public class VehicleExceptions
        extends BaseEntity
        implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_VEHICLEEXCEPTIONS_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;
   
   @ManyToOne(fetch=FetchType.LAZY)
   private Vehicle vehicle;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_EXCEPTION_ID", referencedColumnName="PK_EXCEPTION_ID", nullable=false)
   private Exception exception;
   

   public VehicleExceptions() {
      // TODO Add your own initialization code here.
   }

}
