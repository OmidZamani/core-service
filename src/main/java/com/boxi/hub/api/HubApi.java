package com.boxi.hub.api;
//100101	hub 	HubApi	مدیریت هاب - هاب

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.*;
import com.boxi.hub.payload.request.FilterHub;
import com.boxi.hub.service.HubService;
import com.boxi.transport.payload.request.HubFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/hub")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class HubApi {

    private final HubService hubService;

    //   @PreAuthorize("hasPermission('hasAccess','1001')")
    @GetMapping("/genPinCode")
    public Response genPinCode() {
        log.warn("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }
    @GetMapping("/findById/{id}")
    public HubDto findById(@PathVariable Long id) {
        return hubService.findHubById(id);
    }
    //    @PreAuthorize("hasPermission('hasAccess','10010101')")
    @PostMapping
    public Response createHub(@Valid  @RequestBody HubDto request) {
        log.warn(request.toJson());
        HubDto response = hubService.createHub(request);
        return Response.ok().setPayload(response);
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterHub request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<HubDto> response = hubService.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/filtergroupby")
    public Response filterGroupBy(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                  @RequestBody FilterHub request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<HubDto> response = hubService.filterGroupBy(request, pageable);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/select")
    public Response select(@RequestParam(name = "filter", required = false) String filter,
                           @RequestBody HubFilter hubFilter) {
        log.warn(hubFilter.toString());
        Page<SelectResponse> response = hubService.select(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/selectmainhub")
    public Response selectMainHub(@RequestParam(name = "filter", required = false) String filter,
                                  @RequestBody HubFilter hubFilter) {
        log.warn(hubFilter.toString());
        Page<SelectResponse> response = hubService.selectMainHub(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/selectbranchhub")
    public Response selectBranchHub(@RequestParam(name = "filter", required = false) String filter,
                                    @RequestBody HubFilter hubFilter) {
        log.warn(hubFilter.toString());
        Page<SelectResponse> response = hubService.selectBranchHub(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter", required = false) String filter) {

        Page<SelectResponse> response = hubService.select(filter, null);
        return Response.ok().setPayload(response);
    }


    //   @PreAuthorize("hasPermission('hasAccess','10010102')")
    @PostMapping("/createHubsByExcel")
    public Response createHubsByExcel(@RequestParam("file") MultipartFile excel) throws IOException {
        hubService.createHubsByExcel(excel);
        return Response.ok();
    }

    //  @PreAuthorize("hasPermission('hasAccess','10010103')")
    @PutMapping
    public Response editHub(@RequestBody HubDto request) {
        log.warn(request.toJson());
        HubDto response = hubService.updateHub(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10010104')")
    @DeleteMapping("/{id}")
    public Response deleteHub(@PathVariable Long id) {
        hubService.deleteHub(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return Response.ok().setPayload(hubService.findHubById(id));
    }


    @GetMapping("/zone")
    public Response getZone() {
        return Response.ok().setPayload(hubService.listOfAllZone());
    }


    @GetMapping("/zone/{id}")
    public Response getZone(@PathVariable Long id) {
        return Response.ok().setPayload(hubService.findZoneById(id));
    }

    @DeleteMapping("/zone/delete")
    public Response delete(@RequestBody ZoneHubDto dto) {
        hubService.deleteZoneHub(dto);
        return Response.ok();
    }

    @DeleteMapping("/zone/deleteSubZonePolygon")
    public Response deleteSubZonePolygon(@RequestBody ZoneHubDto dto) {
        hubService.deleteSubZonePolygon(dto);
        return Response.ok();
    }

    @DeleteMapping("/zone/deletepolygon")
    public Response deActiveZoneHub(@RequestBody ZoneHubDto dto) {
        hubService.deActiveZoneHub(dto);
        return Response.ok();
    }

    @GetMapping("/zone/findbyid/{id}")
    public Response findByZoneId(@PathVariable Long id) {
        return Response.ok().setPayload(hubService.findBySubZoneId(id));
    }

    @PostMapping("/zone")
    public Response CreateZone(@RequestBody ZoneDto dto) {
        return Response.ok().setPayload(hubService.createZone(dto));
    }

    @PostMapping("/zone/createSubzone")
    public Response createSubZone(@RequestBody ZoneDto dto) {
        return Response.ok().setPayload(hubService.createSubZone(dto));
    }

    @PostMapping("/zone/position")
    public Response findByPosition(@RequestBody LocationDto dto) {
        return Response.ok().setPayload(hubService.findByPosition(dto.getLocLate(), dto.getLocLong()));
    }

    @GetMapping("/zone/findbycity")
    public Response findByProvince(@RequestParam(name = "city") Long cityId,
                                    @RequestParam(name = "hubname") String hubName,
                                    @RequestParam(name = "type") Long type) {
        return Response.ok().setPayload(hubService.findByCity(cityId, hubName, type));
    }

    @GetMapping("/enterChange/{id}")
    public SimpleHubDto getDataById(@PathVariable Long id) {
        HubDto hubById = hubService.findHubById(id);
        SimpleHubDto simpleHubDto = new SimpleHubDto();
        simpleHubDto.setCode(hubById.getCode());
        simpleHubDto.setId(hubById.getId());
        simpleHubDto.setName(hubById.getName());
        return simpleHubDto;
    }

    @GetMapping("/enterChange/permissionlist")
    public List<HubPermissionDto> permissionlist(@RequestParam(name = "hubcode") String emphublist) {
        log.warn("permissionlist-> " + emphublist);
        String[] split = emphublist.split(",");
        return hubService.listOfHubPermission(split);
    }

    @PostMapping("/enterChange/fetchHubByCodes")
    public List<SimpleHubDto> fetchHubByCodes(@RequestBody SimpleArrayWrapper hubCodes) {
        return hubService.getHubsByCodes(hubCodes);
    }

    @PostMapping("/enterChange/listOfParentHubList")
    public List<SelectResponse> listOfParentHubList(@RequestBody List<Long> listOfHub){
        return hubService.listOfParentHubList(listOfHub);
    }
    @PutMapping("/update")
    public Response updateHub(@RequestBody HubDto hubDto) {
        return Response.ok().setPayload(hubService.updateHubZone(hubDto));
    }

    @GetMapping("/hubLocation")
    public Response hubLocation(@RequestParam(name = "filter") String hubName, @RequestParam(name = "cityid") String cityId) {
        return Response.ok().setPayload(hubService.hubLocation(hubName, cityId));
    }

    @PostMapping("/findRegionInZone")
    public ZoneDto findRegionInZone(@RequestBody FindRegionInZoneDto dto) {
        return hubService.findRegionInZone(dto);
    }

    @GetMapping("/zone/findByRegion/{id}")
    public Response fidByRegion(@PathVariable Long id) {
        return Response.ok().setPayload(hubService.findByZoneRegionPolyGone(id));
    }

    @GetMapping("/zone/findByRegionCountryDevision/{id}")
    public Response findByRegionCountryDivision(@PathVariable Long id) {
        return Response.ok().setPayload(hubService.findByRegionCountryDivision(id));
    }

    @PutMapping("/zone/updateCountryDevision")
    public Response updateCountryDivision(@RequestBody SelectResponse dto) {
        return Response.ok().setPayload(hubService.updateCountryDivision(dto));
    }


    @GetMapping("/findByCityinHub/{id}")
    public Response findByCityInHub(@PathVariable Long id ){
        return Response.ok().setPayload(hubService.findByCityInHub(id));
    }


}
