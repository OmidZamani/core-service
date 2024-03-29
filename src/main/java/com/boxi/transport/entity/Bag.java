package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import com.boxi.transport.enums.BagStatus;
import com.boxi.transport.enums.BagType;
import com.boxi.transport.enums.MisMatchStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;


@Entity(name = "Bag")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_BAG")
public class Bag extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_BAG_ID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true, length = 1)
    private Boolean isActive;

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true, length = 1)
    private Boolean isDeleted;

    @Basic(optional = false)
    @Column(name = "BAGNUMBER", nullable = false, insertable = true, updatable = true)
    private String bagNumber;

    @Basic(optional = true)
    @Column(name = "TYPE", insertable = true, updatable = true)
    private BagType bagType;

    @Basic(optional = true)
    @Column(name = "TRIPID", insertable = true, updatable = true)
    private Long tripId;

    @Basic(optional = true)
    @Column(name = "STATUS", insertable = true, updatable = true)
    private BagStatus status;

    @Basic(optional = false)
    @Column(name = "WEIGHTCAPACITY", nullable = false, insertable = true, updatable = true)
    private BigDecimal weightCapacity;

    @Basic(optional = true)
    @Column(name = "VOLUMECAPACITY", insertable = true, updatable = true)
    private BigDecimal volumeCapacity;

    @Basic(optional = true)
    @Column(name = "ALLOCATEDWEIGHT", insertable = true, updatable = true)
    private BigDecimal allocatedWeight;

    @Basic(optional = true)
    @Column(name = "ALLOCATEDVOLUME", insertable = true, updatable = true)
    private BigDecimal allocatedVolume;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bag")
    private List<BagExceptions> bag;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SOURCEHUB_ID", referencedColumnName = "PK_HUB_ID", nullable = false)
    private Hub sourceHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CONSIGNMENTSDESTHUB_ID", referencedColumnName = "PK_HUB_ID", nullable = true)
    private Hub consignmentsDestinationHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DESINATIONHUB_ID", referencedColumnName = "PK_HUB_ID", nullable = true)
    private Hub destinationHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CURRENTHUB_ID", referencedColumnName = "PK_HUB_ID", nullable = true)
    private Hub currentHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_OWNERHUB_ID", referencedColumnName = "PK_HUB_ID", nullable = true)
    private Hub ownerHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CARRIER_ID", referencedColumnName = "PK_VEHICLE_ID", nullable = true)
    private Vehicle carrier;

//   @ManyToOne(fetch=FetchType.LAZY)
//   @JoinColumn(name="FK_SHELF_ID", referencedColumnName="SHELFID", nullable=true)
//   private Shelf shelf;

    @Basic(optional=true)
    @Column(name="EXTRALOAD", insertable=true, updatable=true)
    private Boolean extraLoad;

    @Basic(optional=true)
    @Column(name="EXTRALOADINVEHICLEID", insertable=true, updatable=true)
    private Long extraLoadInVehicleId;

    @Basic(optional=true)
    @Column(name="MISMATCHSTATUS", insertable=true, updatable=true)
    private MisMatchStatus mismatchStatus;

    @Basic(optional=true)
    @Column(name="LACKOFLOAD", insertable=true, updatable=true)
    private Boolean lackOfLoad;

    public void addBag(BagExceptions newBagExceptions) {
        if (newBagExceptions == null)
            return;
        if (this.bag == null)
            this.bag = new java.util.ArrayList<BagExceptions>();
        if (!this.bag.contains(newBagExceptions)) {
            this.bag.add(newBagExceptions);
            newBagExceptions.setBag(this);
        }
    }


    public void removeBag(BagExceptions oldBagExceptions) {
        if (oldBagExceptions == null)
            return;
        if (this.bag != null)
            if (this.bag.contains(oldBagExceptions)) {
                this.bag.remove(oldBagExceptions);
                oldBagExceptions.setBag((Bag) null);
            }
    }


    public void removeAllBag() {
        if (bag != null) {
            BagExceptions oldBagExceptions;
            for (java.util.Iterator iter = getIteratorBag(); iter.hasNext(); ) {
                oldBagExceptions = (BagExceptions) iter.next();
                iter.remove();
                oldBagExceptions.setBag((Bag) null);
            }
        }
    }

    private Iterator getIteratorBag() {
        return null;
    }

    /**
     * Empty constructor which is required by EJB 3.0 spec.
     */
    public Bag() {
        // TODO Add your own initialization code here.
    }
    public String selectToString() {
        return this.getBagNumber()!=null ? this.getBagNumber()+"":"" ;
    }
}
