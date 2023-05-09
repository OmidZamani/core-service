package com.boxi.hub.payload.dto;

import com.boxi.core.request.DateDto;
import com.boxi.core.request.JsonBase;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.HubCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
public class HubDto extends JsonBase {

    private Long id;

    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private SelectResponse selectHubType;
    private SelectResponse selectParentHub; //can be  null TODO has error

    private SelectResponse selectManager;//TODO look at ui, it is not recommended to add manager id and tel directly it can be ui bad design
    private SelectResponse selectHubCategory;

    private String addressLine; //آدرس
    private String addressDetail;

    @NotNull
    private SelectResponse selectCity;
    private SelectResponse selectState;
    @NotNull
    private SelectResponse selectRegion;
    private Double locLate;
    private Double locLong;
    private DateDto locationStartDate;//
    private Boolean isActive;
    private String pinCode;

    //new filed
    private Boolean isPickupPossible; //امکان جمع آوری
    private Boolean isPossibleConsignmentStorage;
    private Boolean isDeliveryPossible; // امکان توزیع
    private Boolean isPossibleOrderRegistration;
    private Boolean isArrivalScanPossible;//اسکن مرسوله در ورودی هاب
    private List<SelectResponse> hubs;
    private Boolean activeFlag;
}


