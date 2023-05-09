package com.boxi.hub.api;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.CountryDevisionPolygonDto;
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
    public Response findByHubId(@RequestParam(name = "code", required = true) String code, @RequestParam(name = "hubid", required = true) String hubid) {
        List<CountryDevisionPolygonDto> byhubCode = _service.findByhubCode(code, hubid);
        return Response.ok().setPayload(byhubCode);
    }

    @GetMapping("/findbyBaseHub")
    public Response findbyBaseHub(@RequestParam(name = "hubid", required = true) String hubid) {
        CountryDevisionPolygonDto byhubCode = _service.findbyBaseHub(hubid);
        return Response.ok().setPayload(byhubCode);
    }

    @GetMapping
    public Response getProvince(@RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(_service.selectProvince(filter));
    }

    @GetMapping("/provincewithcode")
    public Response getProvincewithcode(@RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(_service.provincewithCode(filter));
    }

    @GetMapping("/province/{provinceId}/city")
    public Response getCity(@PathVariable Long provinceId, @RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(_service.selectCity(provinceId, filter));
    }

    @PostMapping()
    public Response getfindlist(@RequestBody List<SelectResponse> provincelist) {
        return Response.ok().setPayload(_service.selectCityofprovinceBylist(provincelist));
    }

    @GetMapping("/city/{cityId}/loc")
    public Response getLoc(@PathVariable Long cityId, @RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(_service.selectLoc(cityId, filter));
    }

    @GetMapping("/findTreeToParent/{Id}")
    public Response gettree(@PathVariable Long Id) {
        return Response.ok().setPayload(_service.SelectTreeToParent(Id));
    }


    @GetMapping("/findIdincode/{Code}")
    public ContryDevistionSelect findIdincode(@PathVariable String Code) {
        ContryDevistionSelect byidInCodefeign = _service.findByidInCodefeign(new SelectResponse(null, Code));
        return byidInCodefeign;
    }

    @GetMapping("/{id}")
    public SelectResponse getLoc(@PathVariable Long id) {
        return _service.selectById(id);
    }

    @GetMapping("/findById/{id}")
    public CountryDevisionDto getLocfindById(@PathVariable Long id) {
        return _service.selectByIdfindById(id);
    }

    @GetMapping("/findbytype")
    public Response findbytypes(@RequestParam(name = "type", required = true) Long typeId) {
        return Response.ok().setPayload(_service.findByTypes(typeId));
    }

    @GetMapping("/selectByTypes")
    public Response selectByTypes(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(_service.selectByTypes(filter));
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

}
