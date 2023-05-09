package com.boxi.PriceList.payload.request;

import com.boxi.PriceList.payload.dto.ConsignmentInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PriceListRequest extends ConsignmentInfoDto {


}
