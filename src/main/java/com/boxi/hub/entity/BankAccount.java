package com.boxi.hub.entity;

import com.boxi.core.entity.BaseEntity;
import com.boxi.hub.enums.BankType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "BankAccount")
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "TBL_BANKACCOUNT")

public class BankAccount
        extends BaseEntity
         {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_BANKACCOUNT_ID", nullable = false, insertable = true, updatable = true)
    private Long id;

    @Basic(optional = false)
    @Column(name = "ISACTIVE", nullable = false, insertable = true, updatable = true)
    private Boolean isActive;

    @Basic(optional = true)
    @Column(name = "ISDELETED", insertable = true, updatable = true)
    private Boolean isDeleted;

    @Basic(optional = true)
    @Column(name = "ACCOUNTNUMBER", insertable = true, updatable = true)
    private String accountNumber;

    @Basic(optional = true)
    @Column(name = "BANK", insertable = true, updatable = true)
    private BankType bank;

    @Basic(optional = true)
    @Column(name = "ACCOUNTOWNER", insertable = true, updatable = true, length = 254)
    private String accountOwner;

    @Basic(optional = true)
    @Column(name = "ACCOUNTID", insertable = true, updatable = true, length = 254)
    private String accountID;

    @Basic(optional = true)
    @Column(name = "CUSTOMERID", insertable = true, updatable = true)
    private Long customerId;

    @Basic(optional = true)
    @Column(name = "HUBID", insertable = true, updatable = true)
    private Long hubId;


    public BankAccount() {
        // TODO Add your own initialization code here.
    }

}
