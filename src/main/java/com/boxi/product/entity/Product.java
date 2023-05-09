package com.boxi.product.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Product")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_PRODUCT")
public class Product
        extends BaseEntity
        implements java.io.Serializable {


   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_PRODUCT_ID", insertable=true, updatable=true)
   private Long id;


   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=false)
   @Column(name="ISDELETED", nullable=false, insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="CODE", insertable=true, updatable=true, length=254)
   private String code;

   @Basic(optional=true)
   @Column(name="NAME", insertable=true, updatable=true, length=254)
   private String name;

   @Basic(optional=true)
   @Column(name="DESCRIPTION", insertable=true, updatable=true, length=254)
   private String description;
   
   @OneToMany(fetch=FetchType.LAZY, mappedBy="product")
   private List<ProductAttribute> productAttributes;


   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRODUCTGROUP_ID", referencedColumnName="PRODUCTGROUPID", nullable=true)
   private ProductGroup productGroup;
   
   
   
   public void addProductAttributes(ProductAttribute newProductAttribute) {
      if (newProductAttribute == null)
         return;
      if (this.productAttributes == null)
         this.productAttributes = new ArrayList<ProductAttribute>();
      if (!this.productAttributes.contains(newProductAttribute))
      {
         this.productAttributes.add(newProductAttribute);
         newProductAttribute.setProduct(this);
      }
   }
   
   
   public void removeProductAttributes(ProductAttribute oldProductAttribute) {
      if (oldProductAttribute == null)
         return;
      if (this.productAttributes != null)
         if (this.productAttributes.contains(oldProductAttribute))
         {
            this.productAttributes.remove(oldProductAttribute);
            oldProductAttribute.setProduct(null);
         }
   }
   
   
//   public void removeAllProductAttributes() {
//      if (productAttributes != null)
//      {
//         ProductAttribute oldProductAttribute;
//         for (Iterator iter = getIteratorProductAttributes(); iter.hasNext();)
//         {
//            oldProductAttribute = (ProductAttribute)iter.next();
//            iter.remove();
//            oldProductAttribute.setProduct((Product)null);
//         }
//      }
//   }
   
//   public void addProductUsers(Product newProduct) {
//      if (newProduct == null)
//         return;
//      if (this.productUsers == null)
//         this.productUsers = new ArrayList<Product>();
//      if (!this.productUsers.contains(newProduct))
//         this.productUsers.add(newProduct);
//   }
//
//
//   public void removeProductUsers(Product oldProduct) {
//      if (oldProduct == null)
//         return;
//      if (this.productUsers != null)
//         if (this.productUsers.contains(oldProduct))
//            this.productUsers.remove(oldProduct);
//   }
//
//
//   public void removeAllProductUsers() {
//      if (productUsers != null)
//         productUsers.clear();
//   }
   
   public Product() {
      // TODO Add your own initialization code here.
   }

   public String selectToString() {
      return null;
   }
}
