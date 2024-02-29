package com.boxi.hub.api;

import com.boxi.core.request.SimpleWrapper;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.service.CountryDevisionService;
import com.boxi.product.payload.dto.CountryDevisionDto;
import com.boxi.product.response.ContryDevistionSelect;
import com.boxi.utils.AddDefaults;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core-api/countryDevision")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CountryDevisionApi {

    private final CountryDevisionService _service;
    private final AddDefaults addDefaults;


    @GetMapping("/setup_cities")
    public Response add_cities() {
        try {
            addDefaults.initSql();
            return Response.ok();
        } catch (Exception ex) { //checked exception
            ex.printStackTrace();
            return Response.exception();
        }
    }

    @GetMapping("/countrytype")
    public Response getCountryType() {
        return Response.ok().setPayload(_service.getCountryType());
    }

    @PutMapping
    public Response edit(@RequestBody CountryDevisionDto dto) {
        return Response.ok().setPayload(_service.edit(dto));
    }

    @PostMapping("/create")
    public Response create(@RequestBody CountryDevisionDto dto) {
        return Response.ok().setPayload(_service.create(dto));
    }

    @GetMapping("/findbyhub")
    public Response findByHubId(@RequestParam(name = "code") String code, @RequestParam(name = "hubid") String hubid) {

        return Response.ok().setPayload(_service.findByHubCode(code, hubid));
    }

    @GetMapping("/findbyBaseHub")
    public Response findbyBaseHub(@RequestParam(name = "hubid") String hubid) {

        return Response.ok().setPayload(_service.findByBaseHub(hubid));
    }

    @GetMapping
    public Response getProvince(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.selectProvince(filter));
    }

    @GetMapping("/provincewithcode")
    public Response getProvinceWithCode(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.provinceWithCode(filter));
    }

    @GetMapping("/province/{provinceId}/city")
    public Response getCity(@PathVariable Long provinceId, @RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.selectCity(provinceId, filter));
    }

    @PostMapping()
    public Response getFindList(@RequestBody List<SelectResponse> provinceList) {
        return Response.ok().setPayload(_service.selectCityOfProvinceByList(provinceList));
    }

    @GetMapping("/city/{cityId}/loc")
    public Response getLoc(@PathVariable Long cityId, @RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.selectLoc(cityId, filter));
    }

    @GetMapping("/findTreeToParent/{Id}")
    public Response getTree(@PathVariable Long Id) {
        return Response.ok().setPayload(_service.SelectTreeToParent(Id));
    }


    @GetMapping("/findIdincode/{Code}")
    public ContryDevistionSelect findIdincode(@PathVariable String Code) {
        return _service.findByIdInCodeFeign(new SelectResponse(null, Code));
    }

    @GetMapping("/{id}")
    public SelectResponse getLoc(@PathVariable Long id) {
        return _service.selectById(id);
    }

    @GetMapping("/findById/{id}")
    public CountryDevisionDto getLocfindById(@PathVariable Long id) {
        return _service.selectFindById(id);
    }

    @GetMapping("/findbytype")
    public Response findByTypes(@RequestParam(name = "type") Long typeId) {
        return Response.ok().setPayload(_service.findByTypes(typeId));
    }

    @GetMapping("/selectByTypes")
    public Response selectByTypes(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.selectByTypes(filter));
    }
    @GetMapping("/findByName")
    public SelectResponse findByName(@RequestParam(name = "filter") String filter) {
        return _service.findByName(filter);
    }

    @PostMapping("/exchange/selectCityByName")
    public SelectResponse selectCityByName(@RequestBody SimpleWrapper filter) {
        return _service.selectCityByName(filter);
    }

    @PostMapping("/exchange/selectProvinceByName")
    public SelectResponse selectProvinceByName(@RequestBody SimpleWrapper filter) {
        return _service.selectProvinceByName(filter);
    }


    @GetMapping("/findbyCity")
    public Response findByCity(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.findByCity(filter));
    }

    @GetMapping("/findByCityPickup")
    public Response findByCityPickup(@RequestParam(name = "cityId") Long cityId) {
        return Response.ok().setPayload(_service.findByCityPickup(cityId));
    }

    @GetMapping("/findByCityDelivery")
    public Response findByCityDelivery(@RequestParam(name = "cityId") Long cityId) {
        return Response.ok().setPayload(_service.findByCityDelivery(cityId));
    }

    @PostMapping("/findByCityInHub")
    public Response findByCityInHub(@RequestBody CountryDevisionDto dto) {
        return Response.ok().setPayload(_service.findByCityInHub(dto));
    }

    @GetMapping("/findByRegionInCity/{cityId}")
    public SelectResponse findByRegionInCity(@PathVariable Long cityId){
        return _service.findByRegionInCity(cityId);
    }

    @PostMapping("/existsByName")
    public Boolean existsByName(@RequestBody SelectResponse selectResponse){
        return _service.existsByName(selectResponse.getText());
    }

    @GetMapping("/listOfAllCity")
    public List<SelectResponse> listOfAllCity(){
        return _service.listOfAllCity();
    }
}
