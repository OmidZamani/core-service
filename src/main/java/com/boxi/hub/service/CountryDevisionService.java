package com.boxi.hub.service;


import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.CountryDevision;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
import com.boxi.product.payload.dto.ContryDevistionCodeDto;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryDevisionService {

    Page<SelectResponse> selectProvince(String filter);

    Page<SelectResponse> selectCity(Long parentId, String filter);

    Page<SelectResponse>  selectLoc(Long cityId, String filter);

    CountryDevision fromSelect(SelectResponse select) ;

    SelectResponse toSelect(CountryDevision countryDevision);

    List<ContryDevistionSelect> SelectTreeToParent(Long Id);

    boolean existsCountry(String CountryDevision);

    SelectResponse findByidInCode(SelectResponse toCountryDevision);

    CountryDevisionDto findRegioninCtiy(Long city);

    ContryDevistionSelect findByidInCodefeign(SelectResponse toCountryDevision);

    List<SelectResponse> selectCityofprovinceBylist(List<SelectResponse> provincelist);

    SelectResponse selectById(Long id);

    List<ContryDevistionCodeDto> provincewithCode(String filter);


    CountryDevisionDto create(CountryDevisionDto dto);

    CountryDevisionDto edit(CountryDevisionDto dto);

    List<SelectResponse> getCountryType();

    List<CountryDevisionDto> findByhubId(Long id);

    List<CountryDevisionPolygonDto> findByhubCode(String code, String hubid);

    List<SelectResponse> findByTypes(Long typeId);

    List<SelectResponse> selectByTypes(String filter);

    List<SelectResponse> findByCity(String filter);

    CountryDevisionPolygonDto findbyBaseHub(String hubid);

    CountryDevisionDto selectByIdfindById(Long id);

    List<SelectResponse> findByCityPickup(Long cityId);

    List<SelectResponse> findByCityDelivery(Long cityId);
}
