package com.boxi.product.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigInteger;


@Entity(name = "UsingProducts")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_USINGSPRODUCTS")
public class UsingProduct {
       /*هدف:هر پرداکتی که تو پروداکتهای دیگه قابل استفاده است*/

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PK_USINGPRODUCT_ID", nullable=false, insertable=true, updatable=true)
    private Long Id;


    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="FK_CHILD_ID", referencedColumnName="PK_PRODUCT_ID", nullable=false)
    private Product child;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="FK_PARENT_ID", referencedColumnName="PK_PRODUCT_ID", nullable=false)
    private Product parent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FK_PRODUCTATTRIBUTE_ID", referencedColumnName="PK_PRODUCTATTRIBUTE_ID", nullable=true)
    private ProductAttribute productAttribute;


}
