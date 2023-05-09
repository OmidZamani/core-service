
package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity(name="Dock")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name="TBL_DOCK")
public class Dock extends BaseEntity implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_DOCK_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=false)
   @Column(name="CODE", nullable=false, insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=false)
   @Column(name="NAME", nullable=false, insertable=true, updatable=true, length=254)
   private String name;
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_HUB_ID", referencedColumnName="PK_HUB_ID", nullable=false)
   private Hub hub;

   public String selectToString() {
      return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
   }
}
