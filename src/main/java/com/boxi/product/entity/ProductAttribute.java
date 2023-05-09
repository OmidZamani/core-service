/***********************************************************************
 * Module:  ProductAttribute.java
 * Author:  YAS
 * Purpose: Defines the Class ProductAttribute
 ***********************************************************************/

package com.boxi.product.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Entity(name = "ProductAttribute")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_PRODUCTATTRIBUTE")
public class ProductAttribute
//        extends BaseEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PRODUCTATTRIBUTE_ID", insertable = true, updatable = true)
    private Long id;

    @Basic(optional = true)
    @Column(name = "FROMWEIGHT", insertable = true, updatable = true)
    private Double fromWeight;

    @Basic(optional = true)
    @Column(name = "TOWEIGHT", insertable = true, updatable = true)
    private Double toWeight;

    @Basic(optional = true)
    @Column(name = "FROMDIM", insertable = true, updatable = true)
    private Double fromDim;

    @Basic(optional = true)
    @Column(name = "TODIMENSION", insertable = true, updatable = true)
    private Double toDimension;

    @Basic(optional = true)
    @Column(name = "FROMVALUE", insertable = true, updatable = true)
    private BigDecimal fromValue;// بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری

    @Basic(optional = true)
    @Column(name = "TOVALUE", insertable = true, updatable = true)
    private BigDecimal toValue;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PRODUCT_ID", referencedColumnName = "PK_PRODUCT_ID", nullable = false)
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_TIMECOMMITMENT_ID", referencedColumnName = "PK_TIMECOMMITMENT_ID", nullable = false)
    private TimeCommitment timeCommitment;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="productAttribute")
    private List<ProductAttributeDevision> productAttributeDevisions;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="productAttribute")
    private java.util.List<UsingProduct> usingProducts;



    public void addProductAttributeDevisions(ProductAttributeDevision newProductAttributeDevision) {
        if (newProductAttributeDevision == null)
            return;
        if (this.productAttributeDevisions == null)
            this.productAttributeDevisions = new java.util.Vector<ProductAttributeDevision>();
        if (!this.productAttributeDevisions.contains(newProductAttributeDevision))
        {
            this.productAttributeDevisions.add(newProductAttributeDevision);
            newProductAttributeDevision.setProductAttribute(this);
        }
    }


    public void removeProductAttributeDevisions(ProductAttributeDevision oldProductAttributeDevision) {
        if (oldProductAttributeDevision == null)
            return;
        if (this.productAttributeDevisions != null)
            if (this.productAttributeDevisions.contains(oldProductAttributeDevision))
            {
                this.productAttributeDevisions.remove(oldProductAttributeDevision);
                oldProductAttributeDevision.setProductAttribute((ProductAttribute)null);
            }
    }


    public void removeAllProductAttributeDevisions() {
        if (productAttributeDevisions != null)
        {
            ProductAttributeDevision oldProductAttributeDevision;
            for (java.util.Iterator iter = getIteratorProductAttributeDevisions(); iter.hasNext();)
            {
                oldProductAttributeDevision = (ProductAttributeDevision)iter.next();
                iter.remove();
                oldProductAttributeDevision.setProductAttribute((ProductAttribute)null);
            }
        }
    }

    private Iterator getIteratorProductAttributeDevisions() {
        return null;
    }

    public void addUsingProducts(UsingProduct newUsingProduct) {
        if (newUsingProduct == null)
            return;
        if (this.usingProducts == null)
            this.usingProducts = new java.util.ArrayList<UsingProduct>();
        if (!this.usingProducts.contains(newUsingProduct))
        {
            this.usingProducts.add(newUsingProduct);
            newUsingProduct.setProductAttribute(this);
        }
    }


    public void removeUsingProducts(UsingProduct oldUsingProduct) {
        if (oldUsingProduct == null)
            return;
        if (this.usingProducts != null)
            if (this.usingProducts.contains(oldUsingProduct))
            {
                this.usingProducts.remove(oldUsingProduct);
                oldUsingProduct.setProductAttribute((ProductAttribute)null);
            }
    }


    public void removeAllUsingProducts() {
        if (usingProducts != null)
        {
            UsingProduct oldUsingProduct;
            for (java.util.Iterator iter = getIteratorUsingProducts(); iter.hasNext();)
            {
                oldUsingProduct = (UsingProduct)iter.next();
                iter.remove();
                oldUsingProduct.setProductAttribute((ProductAttribute)null);
            }
        }
    }

    private Iterator getIteratorUsingProducts() {
        return null;
    }


    public ProductAttribute() {
        // TODO Add your own initialization code here.
    }

    public String selectToString() {

        return null;
    }
}
