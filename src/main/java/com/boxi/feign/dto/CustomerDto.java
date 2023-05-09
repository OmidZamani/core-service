package com.boxi.feign.dto;

import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CustomerDto extends JsonBase {

    private Long id;

    private Boolean isActive;

    @NotBlank
    private String code;

    @NotBlank
    private String name; //full name

    private SelectResponse selectCustomerType;

    private String nationalCode;

    private String nationalId; //برای شرکت های قدیمی

    private String economicCode;

    private SelectResponse selectParentCustomer;

    private String email;

    private BigDecimal currentCredit;

    private BigDecimal creditLimit;

    private BigDecimal initialCredit;

    //...........................
    private String username;

    private String password;

    //.....................................

    private Boolean extendGlobalVirtualSeries;

    private Boolean dynamicPickupAllocation; //تخصیص خودکار جمع آوری

    //.................

    private Boolean smsNotification;

    private Boolean emailNotification;

    private Boolean pickupPaperWithEmail;

    //.....................................

    private String author;
    private String modifier;
    private Timestamp createdDate;
    private Timestamp modifiedDate;



}
