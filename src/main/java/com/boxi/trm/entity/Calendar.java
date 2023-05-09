package com.boxi.trm.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity(name="Calendar")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_CALENDAR")
public class Calendar
        implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_CALENDAR_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="MILADI", nullable=false, insertable=true, updatable=true, length=254)
   private String miladi;

   @Basic(optional=false)
   @Column(name="SHAMSI", nullable=false, insertable=true, updatable=true, length=254)
   private String shamsi;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="MILADIDATE", insertable=true, updatable=true)
   private Date miladiDate;

   @Basic(optional=true)
   @Column(name="ROWNO", insertable=true, updatable=true)
   private Long rowNo;

   @Basic(optional=true)
   @Column(name="MILADIINT", insertable=true, updatable=true)
   private Long miladiInt;

   @Basic(optional=true)
   @Column(name="SHAMSIINT", insertable=true, updatable=true)
   private Long shamsiInt;

   @Basic(optional=true)
   @Column(name="SHAMSIDAYNAME", insertable=true, updatable=true, length=254)
   private String shamsiDayName;

   @Basic(optional=true)
   @Column(name="SHAMSIDAYNO", insertable=true, updatable=true)
   private Long shamsiDayNO;

   @Basic(optional=true)
   @Column(name="SHAMSIQUARTERNO", insertable=true, updatable=true)
   private Long shamsiQuarterNO;

   @Basic(optional=true)
   @Column(name="SHAMSIQUARTERNAME", insertable=true, updatable=true, length=254)
   private String shamsiQuarterName;

   @Basic(optional=true)
   @Column(name="MONTHNO", insertable=true, updatable=true)
   private Long monthNO;

   @Basic(optional=true)
   @Column(name="MONTHNAME", insertable=true, updatable=true, length=254)
   private String monthName;

   @Basic(optional=true)
   @Column(name="YEARNO", insertable=true, updatable=true)
   private Long yearNO;

   @Basic(optional=true)
   @Column(name="MONTHDAYCOUNT", insertable=true, updatable=true)
   private Long monthDayCount;

   @Basic(optional=true)
   @Column(name="SHAMSIDAYNUMBER", insertable=true, updatable=true)
   private Long shamsiDayNumber;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="MILADIMONTHENDDATE", insertable=true, updatable=true)
   private Date miladiMonthEndDate;


   @OneToMany(fetch=FetchType.LAZY, mappedBy="calendarDate")
   private List<DispatchShift> dispatchShifts;



//   public void addDispatchShifts(DispatchShift newDispatchShift) {
//      if (newDispatchShift == null)
//         return;
//      if (this.dispatchShifts == null)
//         this.dispatchShifts = new java.util.ArrayList<DispatchShift>();
//      if (!this.dispatchShifts.contains(newDispatchShift))
//      {
//         this.dispatchShifts.add(newDispatchShift);
//         newDispatchShift.setCalendarDate(this);
//      }
//   }
//
//
//   public void removeDispatchShifts(DispatchShift oldDispatchShift) {
//      if (oldDispatchShift == null)
//         return;
//      if (this.dispatchShifts != null)
//         if (this.dispatchShifts.contains(oldDispatchShift))
//         {
//            this.dispatchShifts.remove(oldDispatchShift);
//            oldDispatchShift.setCalendarDate((Calendar)null);
//         }
//   }
//
//
//   public void removeAllDispatchShifts() {
//      if (dispatchShifts != null)
//      {
//         DispatchShift oldDispatchShift;
//         for (java.util.Iterator iter = getIteratorDispatchShifts(); iter.hasNext();)
//         {
//            oldDispatchShift = (DispatchShift)iter.next();
//            iter.remove();
//            oldDispatchShift.setCalendarDate((Calendar)null);
//         }
//      }
//   }
//
//   private Iterator getIteratorDispatchShifts() {
//      return null;
//   }
//
//
//   public Calendar() {
//      // TODO Add your own initialization code here.
//   }

}
