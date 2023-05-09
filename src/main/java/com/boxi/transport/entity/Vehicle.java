
package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.Hub;
import com.boxi.transport.enums.FleetType;
import com.boxi.transport.enums.VehicleStatus;
import com.boxi.transport.enums.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity(name = "Vehicle")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "TBL_VEHICLE")
public class Vehicle extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_VEHICLE_ID", nullable = false, insertable = true, updatable = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", insertable = true, updatable = true)
    private Boolean isActive = true;

    @Basic(optional = false)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted = false;

    @Basic(optional = false)
    @Column(name = "VEHICLENUMBER0", nullable = true, insertable = true, updatable = true, length = 254)
    private String vehicleNumber0;

    @Basic(optional = false)
    @Column(name = "VEHICLENUMBER1", nullable = true, insertable = true, updatable = true, length = 254)
    private String vehicleNumber1;


    @Basic(optional = false)
    @Column(name = "VEHICLENUMBER2", nullable = true, insertable = true, updatable = true, length = 254)
    private String vehicleNumber2;


    @Basic(optional = false)
    @Column(name = "VEHICLENUMBER3", nullable = true, insertable = true, updatable = true, length = 254)
    private String vehicleNumber3;

    @Enumerated
    @Column(name = "FLEETTYPE", nullable = true, insertable = true, updatable = true)
    private FleetType fleetType; //  فقط برای vehicle

    @Basic(optional = true)
    @Column(name = "AVAILABLEFORLOCALTRIPS", insertable = true, updatable = true)
    private Boolean availableForLocalTrips;  // فقط برای vehicle

    @Basic(optional = true)
    @Column(name = "AVAILABLEFORUPCOUNTRY", insertable = true, updatable = true)
    private Boolean availableForUpCountry;  // فقط برای vehicle

    @Basic(optional = true)
    @Column(name = "AVAILABLEFORMIDMILE", insertable = true, updatable = true)
    private Boolean availableForMidMile;  // فقط برای vehicle

    @Basic(optional = true)
    @Column(name = "TIMETOSTARTWORK", insertable = true, updatable = true)
    private Timestamp timeToStartWork;

    @Basic(optional = true)
    @Column(name = "TIMETOFINISHWORK", insertable = true, updatable = true)
    private Timestamp timeToFinishWork;

    @Basic(optional = false)
    @Column(name = "WEIGHTCAPACITY", nullable = false, insertable = true, updatable = true)
    private Double weightCapacity;

    @Basic(optional = false)
    @Column(name = "VOLUMECAPACITY", nullable = true, insertable = true, updatable = true)
    private Double volumeCapacity;

    @Basic(optional = false)
    @Column(name = "OWNERSHIPSTATUS", nullable = false, insertable = true, updatable = true)
    private Boolean isOwnerShip=false;

    @Basic(optional=true)
    @Column(name="FIRSTDRIVERID", insertable=true, updatable=true)
    private Long firstDriverId;

    @Basic(optional=true)
    @Column(name="SECONDDRIVERID", insertable=true, updatable=true)
    private Long secondDriverId;

    @Basic(optional=true)
    @Column(name="ALLOCATEDWEIGHT", insertable=true, updatable=true)
    private BigDecimal allocatedWeight;

    @Basic(optional=true)
    @Column(name="ALLOCATEDVOLUME", insertable=true, updatable=true)
    private BigDecimal allocatedVolume; //حجم اختصاص داده شده

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_VEHICLECATEGORY_ID", referencedColumnName = "PK_VEHICLECATEGORY_ID", nullable = true)
    private VehicleCategory vehicleCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_VENDOR_ID", referencedColumnName = "PK_VENDOR_ID", nullable = true)
    private Vendor vendor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_VEHICLEMAKE_ID", referencedColumnName = "PK_VEHICLEMAKE_ID", nullable = false)
    private VehicleMake vehicleMake;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_HUB_ID", referencedColumnName = "PK_HUB_ID", nullable = false)
    private Hub hub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ROUTE_ID", referencedColumnName = "PK_ROUTE_ID", nullable = true)
    private Route route; //اجاره ای  فقط

    @Basic(optional=true)
    @Column(name="STATUS", insertable=true, updatable=true)
    private VehicleStatus status;

    @Basic(optional=true)
    @Column(name="TYPE", insertable=true, updatable=true, table="TBL_VEHICLE")
    private VehicleType type;

    public String selectToString() {
        return "\u202B"+ this.vehicleNumber0+"-" +this.vehicleNumber1+ "-" + this.vehicleNumber2+"-" + this.vehicleNumber3;
    }





}


