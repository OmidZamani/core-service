
package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "TimeCommitment")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_TIMECOMMITMENT")
public class TimeCommitment
        extends BaseEntity
        implements java.io.Serializable {
    /*مدت ارایه خدمت*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TIMECOMMITMENT_ID", nullable = false, insertable = true, updatable = true)
    private Long id;


    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = false)
    @Column(name = "ISDELETED", nullable = false, insertable = true, updatable = true)
    private Boolean isDeleted;

    @Basic(optional = true)
    @Column(name = "NAME", insertable = true, updatable = true)
    private String name;

    @Basic(optional = true)
    @Column(name = "`FROM`", insertable = true, updatable = true)
    private Double from;

    @Basic(optional = true)
    @Column(name = "`TO`", insertable = true, updatable = true)
    private Double to;


    @Basic(optional = true)
    @Column(name = "TIMEUNIT", insertable = true, updatable = true)
    @Enumerated
    private TimeUnit timeUnit;

    @Basic(optional = true)
    @Column(name = "DESCRIPTION", insertable = true, updatable = true, length = 254)
    private String description;

    @Basic(optional = true)
    @Column(name = "PICKUPFROM", insertable = true, updatable = true)
    private Timestamp pickupFrom;

    @Basic(optional = true)
    @Column(name = "PICKUPTO", insertable = true, updatable = true)
    private Timestamp pickupTo;
    @Basic(optional = true)
    @Column(name = "DELIVERYFROM", insertable = true, updatable = true)
    private Timestamp deliveryFrom;
    @Basic(optional = true)
    @Column(name = "DELIVERYTO", insertable = true, updatable = true)
    private Timestamp deliveryTo;

    public TimeCommitment() {
        // TODO Add your own initialization code here.
    }


    public String selectToString() {
        return (this.getName()) + (Constants.separator) + "(" +
                (this.getFrom() != null ? this.getFrom().intValue() : "") + (Constants.separator) + " تا " +
                (this.getTo() != null ? this.getTo().intValue() : "") + (Constants.separator) + " " +
                (this.getTimeUnit() != null ? this.getTimeUnit() : "") + (Constants.separator) + ")"
                ;
    }
}
