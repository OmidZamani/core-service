package com.boxi.trm.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import com.boxi.trm.enums.DispatchShiftType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity(name="DispatchShift")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_DISPATCHSHIFT")
public class DispatchShift
        extends BaseEntity
        implements Serializable {

   @Id
   @Column(name="PK_DISPATCHSHIFT_ID", nullable=false, insertable=true, updatable=true)
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true, length=1)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true, length=1)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="TIMEFROM", insertable=true, updatable=true, length=6)
   private Timestamp timeFrom;

   @Basic(optional=true)
   @Column(name="TIMETO", insertable=true, updatable=true, length=6)
   private Timestamp timeTo;

   @Basic(optional=true)
   @Column(name="PRIORATY", insertable=true, updatable=true)
   private Long prioraty;

   @Basic(optional=false)
   @Column(name="DISPATCHSHIFTTYPE", nullable=false, insertable=true, updatable=true)
   private DispatchShiftType dispatchShiftType;

   @ManyToOne(optional=false, fetch=FetchType.LAZY)
   @JoinColumn(name="FK_CALENDARDATE_ID", referencedColumnName="PK_CALENDAR_ID", nullable=false)
   private Calendar calendarDate;

   @ManyToOne(optional=false)
   @JoinColumn(name="FK_HUB_ID", referencedColumnName="PK_HUB_ID", nullable=false)
   private Hub hub;

   @ManyToOne(optional=false)
   @JoinColumn(name="FK_CALENDARHUB_ID", referencedColumnName="CALENDARHUBID", nullable=false)
   private CalendarHub calendarHub;



   public DispatchShift() {
      // TODO Add your own initialization code here.
   }

}
