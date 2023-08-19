package com.boxi.transport.api;
//100803	vehicle	VehicleApi	حمل و نقل - وسیله نقلیه

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;

import com.boxi.transport.enums.FleetType;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.service.VehicleService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/core-api/vehicle")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VehicleApi {

    private final VehicleService _service;

    private final ConvertExcelServiceImpl convertExcelService;


    // @PreAuthorize("hasPermission('hasAccess','10080301')")
    @PostMapping
    public Response create(@RequestBody VehicleDto request) {
        log.warn(request.toJson());
        VehicleDto response = _service.create(request);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/client")
    public VehicleDto clientCreate(@RequestBody VehicleDto request) {//TODO Transactional
        log.warn(request.toJson());

        return _service.createClient(request);
    }

    @PutMapping("/client")
    public VehicleDto clientUpdate(@RequestBody VehicleDto request) {//TODO Transactional
        log.warn(request.toJson());

        return _service.edit(request);
    }

    @PostMapping("/addvehicleclinet")
    public VehicleDto createClient(@RequestBody VehicleDto request) {//TODO Transactional
        log.warn(request.toJson());
        request.setFleetTypeSelect(new SelectResponse(FleetType.justForHub.getValue(), FleetType.justForHub.getFa()));
        return _service.create(request);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080303')")
    @PutMapping
    public Response edit(@RequestBody VehicleDto request) {
        log.warn(request.toJson());
        VehicleDto response = _service.edit(request);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterVehicle request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<VehicleDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter,
                           @RequestBody HubFilter hubFilter) {
        return Response.ok().setPayload(_service.select(filter, hubFilter));
    }

    // @PreAuthorize("hasPermission('hasAccess','10080304')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return Response.ok();
    }

    @GetMapping("/selectFleetTypes")
    public Response selectFleetTypes() {
        return Response.ok().setPayload(FleetType.select());
    }


    // @PreAuthorize("hasPermission('hasAccess','10080302')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {

        log.warn(Entity);

        List<VehicleExcelDto> vehicleExcels =
                (List<VehicleExcelDto>) convertExcelService.ConvertExcelToObjects(VehicleExcelDto.class, excel);

        if (_service.ExcelValidation(vehicleExcels)) {

            List<VehicleDto> bags = _service.ImportExcel(vehicleExcels);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("vehicle", bags.size());
            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.exception();
        }
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable Long id) {
        return _service.clientFindById(id);
    }

    @GetMapping("/findById/{id}")
    public Response getfindByIdehicleById(@PathVariable Long id) {
        return Response.ok().setPayload(_service.clientFindById(id));
    }{}

    @PostMapping("/updateAssignable")
    public VehicleDto updateAssignable(@RequestBody VehicleDto dto) {
        return _service.updateAssignable(dto);
    }


    @PostMapping("/findbycartag")
    public VehicleDto findByCarTag(@RequestBody CarTagDto dto) {
        return _service.clientFindByCarTag(dto);
    }

    @GetMapping("/vehicletype")
    public Response getVehicleType() {
        return Response.ok().setPayload(_service.getVehicleType());
    }


    @PostMapping("/createException")
    public Response createException(@RequestBody VehicleExceptionsDto dto) {
        return Response.ok().setPayload(_service.createException(dto));
    }

    @PostMapping("/findVehicleException")
    public List<VehicleExceptionsDto> findVehicleException(@RequestBody VehicleExceptionsDto dto) {
        return _service.findVehicleException(dto);
    }

    @PostMapping("/findbycartagnumber")
    public Response findByCarTagNumber(@RequestBody CarTagDto dto) {
        return Response.ok().setPayload(_service.clientFindByCarTag(dto));
    }

    @PostMapping("/findbycartagnumberSuggest")
    public Response findByCarTagNumberSuggest(@RequestBody CarTagDto dto) {
        return Response.ok().setPayload(_service.findByCarTagNumberSuggest(dto));
    }

    @GetMapping("/findBytype")
    public List<Long> findByType(@RequestParam(name = "type") Long type, @RequestParam(name = "hub") Long hubId) {
        return _service.findByType(type, hubId);
    }

    @GetMapping("/findByMake")
    public List<Long> findByMake(@RequestParam(name = "type") Long type, @RequestParam(name = "hub") Long hubId) {
        return _service.findByMake(type, hubId);
    }

    @GetMapping("/findByMakeById/{id}")
    public VehicleMakeDto findByMake(@PathVariable Long id) {
        return _service.findByMakeById(id);
    }


    @GetMapping("/findByVendore")
    public List<Long> findByVendor(@RequestParam(name = "id") Long id) {
        return _service.findByVendor(id);
    }

    @PostMapping("/filterByClient")
    public List<VehicleDto> filterByClient(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                           @RequestBody FilterVehicle request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<VehicleDto> response = _service.filter(request, pageable);
        return response.stream().collect(Collectors.toList());
    }

    @PostMapping("/listOfVehicleInHub")
    public List<VehicleDto> listOfVehicleInHub(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                               @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                               @RequestBody FilterVehicle request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return _service.listOfVehicleInHub(request, pageable).stream().collect(Collectors.toList());
    }
    @PostMapping("/listOfAdmVehicleInHub")
    public List<AdmVehicleDto> listOfAdmVehicleInHub(@RequestBody AdmVehicleDto dto) {
        return _service.listOfAdmVehicleInHub(dto);

    }


}
