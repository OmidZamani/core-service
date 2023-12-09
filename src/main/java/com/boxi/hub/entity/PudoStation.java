package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "PudoStation")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_PUDOSTATION")
public class PudoStation
        extends BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PUDOSTATION_ID", nullable = false, insertable = true, updatable = true)
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

    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    @Column(name = "LOCATIONSTARTDATE", insertable = true, updatable = true)
    private Date locationStartDate;

    @Basic(optional = true)
    @Column(name = "LOCLATE", insertable = true, updatable = true)
    private Double locLate;

    @Basic(optional = true)
    @Column(name = "LOCLONG", insertable = true, updatable = true)
    private Double locLong;

    @Basic(optional = true)
    @Column(name = "ADDRESSLINE", insertable = true, updatable = true, length = 254)
    private String addressLine;

    @Basic(optional = true)
    @Column(name = "ADDRESSDETAIL", insertable = true, updatable = true, length = 254)
    private String addressDetail;

    @Basic(optional = true)
    @Column(name = "ISPICKUPPOSSIBLE", insertable = true, updatable = true)
    private Boolean isPickupPossible;

    @Basic(optional = true)
    @Column(name = "ISDELIVERYPOSSIBLE", insertable = true, updatable = true)
    private Boolean isDeliveryPossible;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @Column(name = "FK_HUB", insertable = true, updatable = true)
//    private Hub hub;
    public PudoStation() {
        // TODO Add your own initialization code here.
    }

}
