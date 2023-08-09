package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Entity(name = "DiscountCode")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_DISCOUNTCODE")
public class DiscountCode
        extends BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_DISCOUNTCODE_ID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = false)
    @Column(name = "DISCOUNTCODE", nullable = false, insertable = true, updatable = true, length = 254)
    private String discountCode;

    @Basic(optional = true)
    @Column(name = "ISPUBLIC", insertable = true, updatable = true)
    private Boolean isPublic;

    @Basic(optional = true)
    @Column(name = "ONETIMEDISCOUNTCODE", insertable = true, updatable = true)
    private Boolean oneTimeDiscountCode;

    @Basic(optional = true)
    @Column(name = "AMOUNT", insertable = true, updatable = true)
    private BigDecimal amount;

    @Basic(optional = true)
    @Column(name = "PERCENT", insertable = true, updatable = true)
    private BigDecimal percent;

    @Basic(optional = false)
    @Column(name = "VALIDDATEFROM", nullable = false, insertable = true, updatable = true)
    private Timestamp validDateFrom;

    @Basic(optional = false)
    @Column(name = "VALIDDATETO", nullable = false, insertable = true, updatable = true)
    private Timestamp validDateTo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "discountCode")
    private List<DiscountCodesArrangement> arrangements;


    public void addArrangements(DiscountCodesArrangement newDiscountCodesArrangement) {
        if (newDiscountCodesArrangement == null)
            return;
        if (this.arrangements == null)
            this.arrangements = new ArrayList<DiscountCodesArrangement>();
        if (!this.arrangements.contains(newDiscountCodesArrangement)) {
            this.arrangements.add(newDiscountCodesArrangement);
            newDiscountCodesArrangement.setDiscountCode(this);
        }
    }


    public void removeArrangements(DiscountCodesArrangement oldDiscountCodesArrangement) {
        if (oldDiscountCodesArrangement == null)
            return;
        if (this.arrangements != null)
            if (this.arrangements.contains(oldDiscountCodesArrangement)) {
                this.arrangements.remove(oldDiscountCodesArrangement);
                oldDiscountCodesArrangement.setDiscountCode((DiscountCode) null);
            }
    }


    public void removeAllArrangements() {
        if (arrangements != null) {
            DiscountCodesArrangement oldDiscountCodesArrangement;
            for (Iterator iter = getIteratorArrangements(); iter.hasNext(); ) {
                oldDiscountCodesArrangement = (DiscountCodesArrangement) iter.next();
                iter.remove();
                oldDiscountCodesArrangement.setDiscountCode((DiscountCode) null);
            }
        }
    }

    private Iterator getIteratorArrangements() {
        return null;
    }


    public DiscountCode() {
        // TODO Add your own initialization code here.
    }

}
