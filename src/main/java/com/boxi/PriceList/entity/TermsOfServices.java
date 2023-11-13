package com.boxi.PriceList.entity;

import com.boxi.PriceList.Enum.ConsignmentType;
import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.entity.CountryDevision;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity(name="TermsOfServices")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_TERMSOFSERVICES")
public class TermsOfServices
        extends BaseEntity
        implements Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_TERMSOFSERVICES_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=false)
   @Column(name="SERVICENAME", nullable=false, insertable=true, updatable=true, length=254)
   private String serviceName;

   @Basic(optional=false)
   @Column(name="SERVICETYPE", nullable=false, insertable=true, updatable=true)
   private ServiceType serviceType;

   @Basic(optional=true)
   @Column(name="SERVICEDESCRIPTION", insertable=true, updatable=true, length=254)
   private String serviceDescription;

   @Basic(optional=false)
   @Temporal(TemporalType.DATE)
   @Column(name="SERVICEVALIDDATEFROM", nullable=false, insertable=true, updatable=true)
   private Date serviceValidDateFrom;

   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="SERVICEVALIDDATETO", insertable=true, updatable=true)
   private Date serviceValidDateTo;

   @Basic(optional=true)
   @Column(name="CONSIGNMENTTYPE", insertable=true, updatable=true)
   private ConsignmentType consignmentType;

   @Basic(optional=true)
   @Column(name="PRICE", insertable=true, updatable=true)
   private BigDecimal price;

   @Basic(optional=true)
   @Column(name="PRICEFORMULE", insertable=true, updatable=true, length=254)
   private String priceFormule;

   @Basic(optional=true)
   @Column(name="FROMWEIGHT", insertable=true, updatable=true)
   private Double fromWeight;

   @Basic(optional=true)
   @Column(name="TOWEIGHT", insertable=true, updatable=true)
   private Double toWeight;

   @Basic(optional=true)
   @Column(name="FROMDIM", insertable=true, updatable=true)
   private Double fromDim;

   @Basic(optional=true)
   @Column(name="TODIMENSION", insertable=true, updatable=true)
   private Double toDimension;

   @Basic(optional=true)
   @Column(name="FROMVALUE", insertable=true, updatable=true)
   private BigDecimal fromValue;

   @Basic(optional=true)
   @Column(name="TOVALUE", insertable=true, updatable=true)
   private BigDecimal toValue;

   @Basic(optional=true)
   @Column(name="FROMNUMBER", insertable=true, updatable=true)
   private Long fromNumber;

   @Basic(optional=true)
   @Column(name="TONUMBER", insertable=true, updatable=true)
   private Long toNumber;

   @Basic(optional=true)
   @Column(name="MINIMUMORDERQUANTITY", insertable=true, updatable=true)
   private Long minimumOrderQuantity;

   @Basic(optional=false)
   @Column(name="TIMECOMMITMENTFROM", nullable=false, insertable=true, updatable=true)
   private Double timeCommitmentFrom;

   @Basic(optional=false)
   @Column(name="TIMECOMMITMENTTO", nullable=false, insertable=true, updatable=true)
   private Double timeCommitmentTo;

   @Basic(optional=false)
   @Column(name="TIMECOMMITMENTTIMEUNIT", nullable=false, insertable=true, updatable=true)
   private Long timeCommitmentTimeUnit;

   @Basic(optional=false)
   @Column(name="TIMECOMMITMENTID",  insertable=true, updatable=true)
   private Long timeCommitmentId;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_TOCITY_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=true)
   private CountryDevision toCity;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_FROMCITY_ID", referencedColumnName="PK_COUNTRYDEVISION_ID", nullable=true)
   private CountryDevision fromCity;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_SERVICE_ID", referencedColumnName="PK_SERVICE_ID", nullable=true)
   private Services service;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PARENTSERVICE_ID", referencedColumnName="PK_SERVICE_ID", nullable=true)
   private Services parentService;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="FK_PRICELISTDETAIL_ID", referencedColumnName="PK_PRICELISTDETAIL_ID", nullable=true)
   private PriceListDetail priceListDetail;

   public TermsOfServices() {
      // TODO Add your own initialization code here.
   }

}
