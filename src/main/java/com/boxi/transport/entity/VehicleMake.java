
package com.boxi.transport.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.transport.enums.FuelType;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity(name = "VehicleMake")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "TBL_VEHICLEMAKE")
public class VehicleMake extends BaseEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_VEHICLEMAKE_ID", nullable = false, insertable = true, updatable = false)
    private Long id;


    @Basic(optional = false)
    @Column(name = "ISACTIVE", insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = false)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted = false;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 254)
    private String name;

    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, insertable = true, updatable = true, length = 254)
    private String code;

    @Basic(optional = true)
    @Column(name = "WEIGHTCAPACITY", insertable = true, updatable = true)
    private Double weightCapacity;

    @Basic(optional = true)
    @Column(name = "VOLUMECAPACITY", insertable = true, updatable = true)
    private Double volumeCapacity;

    @Basic(optional = true)
    @Column(name = "CONSIGNMENTCAPACITY", insertable = true, updatable = true)
    private Double consignmentCapacity;


    @Enumerated
    @Column(name = "FUELTYPE", insertable = true, updatable = true)
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "FK_VENDOR_ID", referencedColumnName = "PK_VENDOR_ID", nullable = true)
    private Vendor vendor; //نام خودرو ساز


    public String selectToString() {
        return (this.getName()!=null ? this.getName():"" )+ (Constants.separator) +(this.getCode()!=null ?this.getCode():"");
    }
}
