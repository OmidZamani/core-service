package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.*;
import javax.persistence.*;

@Entity(name="BankAccount")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name="TBL_BANKACCOUNT")
public class BankAccount
        extends BaseEntity
        implements java.io.Serializable {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="PK_BANKACCOUNT_ID", nullable=false, insertable=true, updatable=true)
   private Long id;

   @Basic(optional=false)
   @Column(name="ISACTIVE", nullable=false, insertable=true, updatable=true)
   private Boolean isActive;

   @Basic(optional=true)
   @Column(name="ISDELETED", insertable=true, updatable=true)
   private Boolean isDeleted;

   @Basic(optional=true)
   @Column(name="ACCOUNTNUMBER", insertable=true, updatable=true)
   private Long accountNumber;

   @Basic(optional=true)
   @Column(name="BANK", insertable=true, updatable=true)
   private Long bank;
   

   public BankAccount() {
      // TODO Add your own initialization code here.
   }

}
//ENUM
//meli	 ملی
//sepah	 سپه
//sanatMadan	 صنعت و معدن
//keshavarzi	 کشاورزی
//maskan	 مسکن
//toseSaderat	 توسعه صادرات
//toseTaavon	 توسعه تعاون
//postBank	 پست بانک
//eghtesadNovin	 اقتصاد نوین
//parsian	 پارسیان
//karafarin	 کشاورزی
//saman	 سامان
//sina	 سینا
//khavarmianeh	 کشاورزی
//shahr	 شهر
//dey	 دی
//saderat	 صادرات
//melat	 ملت
//tejarat	 تجارت
//refah	 رفاه
//gardeshgari	 گردشگری
//iranzamin	 ایران زمین
//sarmaye	 سرمایه
//pasargad	 پاسارگاد
//ayande	 آینده
//mehrIran	 مهر ایران
//resalat	 رسالت