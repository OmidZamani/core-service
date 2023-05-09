package com.boxi.transport.payload.excel;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateHubExcelRequest {

    @NotNull
    private String code;//ok

    @NotNull
    private String name;//ok

    @NotNull
    private String hubType; //need map ok
    private String  parentHubCode; //need map ok
    private Boolean mandatoryArrivalScan;//اسکن مرسوله در ور.دی هاب
    private String managerCode; //need map ok
    private String hubCategoryName; //need map ok
    private String addressLine1;
    private String addressLine2;
    private String cityName; // need map ok
    private String stateName; //need map ok
    private String regionName; //need map ok
    private Double locLate;
    private Double locLong;
    private String plateNumber;
    private Boolean dropOffAllowed; // امکان تحویل مرسوله دارد
    private Date locationStartDate;
    private Boolean isActive;
    private String pinCode;
}
