package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "ProductGroup")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_PRODUCTGROUP")
public class ProductGroup
        extends BaseEntity
        implements java.io.Serializable {
     // فقط جنبه گزارشی دارد

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTGROUPID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional=false)
    @Column(name="ISDELETED", nullable=false, insertable=true, updatable=true)
    private Boolean isDeleted ;

    @Basic(optional = true)
    @Column(name = "CODE", insertable = true, updatable = true, length = 254)
    private String code;

    @Basic(optional = true)
    @Column(name = "NAME", insertable = true, updatable = true, length = 254)
    private String name;

    @Basic(optional = true)
    @Column(name = "DESCRIPTION", insertable = true, updatable = true, length = 254)
    private String description;


    public ProductGroup() {
        // TODO Add your own initialization code here.
    }
    public String selectToString() {
        return (this.getName() != null ? this.getName() : "") + (Constants.separator) + (this.getCode() != null ? this.getCode() : "");
    }
}
