package com.boxi.feign.dto;


import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DriverDto implements Serializable {
    private Long id;
    private Boolean isActive;
    private Boolean isDeleted;
    private String name;
    private Long mobile;
    private DateDto startDate;
    private DateDto endDate;
    private String drivingLicenceNumber;
    private DateDto drivingLicenseExpiry;
    private Long hubId;
    private SelectResponse employee;
    private SelectResponse user;
    private SelectResponse thirdParty;
    private SelectResponse isPresence;
}











