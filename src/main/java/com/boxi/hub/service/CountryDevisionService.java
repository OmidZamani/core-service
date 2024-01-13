package com.boxi.hub.service;


import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.product.payload.dto.ContryDevistionCodeDto;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import org.h2.command.dml.Select;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryDevisionService {

    Page<SelectResponse> selectProvince(String filter);

    Page<SelectResponse> selectCity(Long parentId, String filter);

    Page<SelectResponse>  selectLoc(Long cityId, String filter);

    CountryDevision fromSelect(SelectResponse select) ;

    SelectResponse toSelect(CountryDevision countryDevision);

    List<ContryDevistionSelect> SelectTreeToParent(Long Id);

    boolean existsCountry(String CountryDivision);

    SelectResponse findByIdInCode(SelectResponse toCountryDivision);

    CountryDevisionDto findRegionInCity(Long city);//TODO

    ContryDevistionSelect findByIdInCodeFeign(SelectResponse toCountryDivision);

    List<SelectResponse> selectCityOfProvinceByList(List<SelectResponse> provinceList);

    SelectResponse selectById(Long id);

    List<ContryDevistionCodeDto> provinceWithCode(String filter);


    CountryDevisionDto create(CountryDevisionDto dto);

    CountryDevisionDto edit(CountryDevisionDto dto);

    List<SelectResponse> getCountryType();

    List<CountryDevisionDto> findByHubId(Long id); //TODO

    List<CountryDevisionPolygonDto> findByHubCode(String code, String hubId);

    List<SelectResponse> findByTypes(Long typeId);

    List<SelectResponse> selectByTypes(String filter);

    List<SelectResponse> findByCity(String filter);

    CountryDevisionPolygonDto findByBaseHub(String hubId);

    CountryDevisionDto selectFindById(Long id);

    List<SelectResponse> findByCityPickup(Long cityId);

    List<SelectResponse> findByCityDelivery(Long cityId);

    List<SelectResponse> findByCityInHub(CountryDevisionDto dto);

    SelectResponse findByName(String filter);

    SelectResponse findByRegionInCity(Long cityId);
}
