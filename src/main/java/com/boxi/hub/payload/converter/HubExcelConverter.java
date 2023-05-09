package com.boxi.hub.payload.converter;


import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.entity.HubCategory;
import com.boxi.hub.enums.HubType;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.hub.repo.CountryDevisionRepository;
import com.boxi.hub.repo.HubCategoryRepository;
import com.boxi.hub.repo.HubRepository;
import com.boxi.transport.payload.excel.CreateHubExcelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Slf4j

public class HubExcelConverter {
    private final String separator = " - ";

    @Autowired
    HubRepository hubRepository;
    
    @Autowired
    HubCategoryRepository hubCategoryRepository ;
    
    @Autowired
    CountryDevisionRepository countryDevisionRepository;


    public HubDto excelToDto(CreateHubExcelRequest createHubExcelRequest) {
        HubDto hubDto = new HubDto();
        BeanUtils.copyProperties(createHubExcelRequest, hubDto);
        //....................
        if (!StringUtils.hasText(createHubExcelRequest.getCode()))
            throw BusinessException.valueException(EntityType.Hub, "code.can.not.be.empty");
        if (hubRepository.existsByCodeAndIsDeletedFalse(createHubExcelRequest.getCode()))
            throw BusinessException.valueException(EntityType.Hub, "hub.code.not.found",createHubExcelRequest.getCode());
        hubDto.setCode(createHubExcelRequest.getCode());
        //......................
        if (!StringUtils.hasText(createHubExcelRequest.getName()))
            throw BusinessException.valueException(EntityType.Hub, "name.can.not.be.empty");
        if (hubRepository.existsByName(createHubExcelRequest.getName()))
            throw BusinessException.valueException(EntityType.Hub, "hub.name.exist",createHubExcelRequest.getName());
        hubDto.setName(createHubExcelRequest.getName());
        //.....................
        if (!StringUtils.hasText(createHubExcelRequest.getHubType()))
            throw BusinessException.valueException(EntityType.Hub, "hub.type.can.not.be.empty");
        HubType hubType = HubType.findByFa(createHubExcelRequest.getHubType());
        hubDto.setSelectHubType(new SelectResponse(hubType.getValue(),""));
        //.......................
        if (StringUtils.hasText(createHubExcelRequest.getParentHubCode())) {
            String parentHubCode = createHubExcelRequest.getParentHubCode();
            Hub parent = hubRepository.findTopByCode(parentHubCode);
            if (parent == null) {
                throw BusinessException.valueException(EntityType.Hub, "parent.not.found",parentHubCode);
            }
            hubDto.setSelectParentHub(new SelectResponse(parent.getId(),""));
        }
        //.......................
        if (StringUtils.hasText(createHubExcelRequest.getManagerCode())) {
            //TODO after security
        }
        //.......................
        if (StringUtils.hasText(createHubExcelRequest.getHubCategoryName())) {
            String cat = createHubExcelRequest.getHubCategoryName();
            HubCategory hubCategory = hubCategoryRepository.findTopByNameLike(cat);
            if (hubCategory == null) {
                throw BusinessException.valueException(EntityType.Hub,  "hub.category.not.found");
            }
            hubDto.setSelectHubCategory(new SelectResponse(hubCategory.getId(),""));
        }
        //.......................
        if (StringUtils.hasText(createHubExcelRequest.getStateName())) {
            String stateName = createHubExcelRequest.getStateName();
            CountryDevision state = countryDevisionRepository.findTopByName(stateName);
            if (state == null) {
                throw BusinessException.valueException(EntityType.Hub, "state.not.found");
            } else {
                hubDto.setSelectState(new SelectResponse(state.getId(),""));
                //...................................
                String cityName = createHubExcelRequest.getCityName();
                CountryDevision city = countryDevisionRepository.findTopByNameAndParent(cityName, state);
                if (city == null) {
                    throw BusinessException.valueException(EntityType.Hub, "city.not.found");
                } else {
                    hubDto.setSelectCity(new SelectResponse(city.getId(),""));
                    String regionName = createHubExcelRequest.getRegionName();
                    CountryDevision region = countryDevisionRepository.findTopByNameAndParent(regionName, city);
                    if (region == null) {
                        throw BusinessException.valueException(EntityType.Hub, "region.not.found");
                    } else {
                        hubDto.setSelectRegion(new SelectResponse(region.getId(),""));
                    }
                }
            }
            //...............................
   /*         if(createHubExcelRequest.getLocLate()!=null)
                hubDto.setLocLate(createHubExcelRequest.getLocLate());
            if(createHubExcelRequest.getLocLong()!=null)
                hubDto.setLocLong(createHubExcelRequest.getLocLong());*/
        }
        return hubDto;
    }
}
