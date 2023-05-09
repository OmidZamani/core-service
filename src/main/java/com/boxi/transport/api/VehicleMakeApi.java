package com.boxi.transport.api;
//100802	vehicle make	VehicleMakeApi	حمل و نقل - مدل وسیله نقلیه
import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.Response;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.entity.VehicleMake;
import com.boxi.transport.enums.FuelType;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.VehicleMakeFilter;
import com.boxi.transport.service.VehicleMakeService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/core-api/vehicleMake")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class VehicleMakeApi {

    private final VehicleMakeService _service;

    private final ConvertExcelServiceImpl convertExcelService;

    // @PreAuthorize("hasPermission('hasAccess','10080201')")
    @PostMapping
    public Response create(@RequestBody VehicleMakeDto request) {
        log.warn(request.toJson());
        VehicleMakeDto response=_service.create(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080203')")
    @PutMapping
    public Response edit(@RequestBody VehicleMakeDto request) {
        log.warn(request.toJson());
        VehicleMakeDto response=_service.edit(request);
        return  Response.ok().setPayload(response);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody VehicleMakeFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<VehicleMakeDto> response = _service.filter(request, pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        return Response.ok().setPayload(_service.select(filter));
    }

    @GetMapping("/selectFuelTypes")
    public Response selectFuelTypes() {
            return  Response.ok().setPayload(FuelType.select());
    }


    // @PreAuthorize("hasPermission('hasAccess','10080204')")
    @DeleteMapping("/{id}")
    public Response deleteHub(@PathVariable Long id) {
        _service.delete(id);
        return  Response.ok();
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return  Response.ok().setPayload(_service.get(id));
    }


    // @PreAuthorize("hasPermission('hasAccess','10080202')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);

        List<VehicleMakeExcelDto> vehicleMakeExcelDtos =
                (List<VehicleMakeExcelDto>) convertExcelService.ConvertExcelToObjects(VehicleMakeExcelDto.class, excel);

        if (_service.ExcelValidation(vehicleMakeExcelDtos)) {

            List<VehicleMakeDto> vendorExcelDtos1 = _service.ImportExcel(vehicleMakeExcelDtos);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("vehicleMake",vendorExcelDtos1.size());
            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.exception();
        }
    }
}
