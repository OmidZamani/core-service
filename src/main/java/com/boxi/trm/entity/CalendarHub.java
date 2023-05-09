package com.boxi.trm.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity(name = "CalendarHub")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_CALENDARHUB")
public class CalendarHub
        extends BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CALENDARHUBID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted;

    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, insertable = true, updatable = true, length = 254)
    private String code;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 254)
    private String name;

    @OneToMany(mappedBy = "calendarHub")
    private List<DispatchShift> dispatchShifts;


    @Basic(optional = false)
    @Column(name = "YEARNO", insertable = true, updatable = true,nullable = false)
    private Long yearNO;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_HUB_ID", referencedColumnName = "PK_HUB_ID", nullable = false)
    private Hub hub;


    public void addDispatchShifts(DispatchShift newDispatchShift) {
        if (newDispatchShift == null)
            return;
        if (this.dispatchShifts == null)
            this.dispatchShifts = new ArrayList<DispatchShift>();
        if (!this.dispatchShifts.contains(newDispatchShift)) {
            this.dispatchShifts.add(newDispatchShift);
            newDispatchShift.setCalendarHub(this);
        }
    }


    public void removeDispatchShifts(DispatchShift oldDispatchShift) {
        if (oldDispatchShift == null)
            return;
        if (this.dispatchShifts != null)
            if (this.dispatchShifts.contains(oldDispatchShift)) {
                this.dispatchShifts.remove(oldDispatchShift);
                oldDispatchShift.setCalendarHub((CalendarHub) null);
            }
    }


    public void removeAllDispatchShifts() {
        if (dispatchShifts != null) {
            DispatchShift oldDispatchShift;
            for (Iterator iter = getIteratorDispatchShifts(); iter.hasNext(); ) {
                oldDispatchShift = (DispatchShift) iter.next();
                iter.remove();
                oldDispatchShift.setCalendarHub((CalendarHub) null);
            }
        }
    }

    private Iterator getIteratorDispatchShifts() {
        return null;
    }

    /**
     * Empty constructor which is required by EJB 3.0 spec.
     */
    public CalendarHub() {
        // TODO Add your own initialization code here.
    }

}
