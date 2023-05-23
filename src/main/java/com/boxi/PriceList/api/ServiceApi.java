package com.boxi.PriceList.api;
//1005	service def	ServiceApi	تعریف سرویس

import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.payload.request.FilterService;
import com.boxi.PriceList.service.ServiceService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
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

@RequestMapping("/core-api/service")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ServiceApi {

    private final ServiceService serviceService;

    private final ConvertExcelServiceImpl convertExcelService;


    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }

    // @PreAuthorize("hasPermission('hasAccess','100501')")
    @PostMapping
    public Response create(@RequestBody ServiceDto request) {
        log.warn(request.toJson());
        request.setIsDeleted(false);
        ServiceDto response = serviceService.create(request);
        return Response.ok().setPayload(response);
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterService request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ServiceDto> response = serviceService.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/selectbyedit")
    public Response selectByEdit(@RequestParam(name = "filter") String filter) {

        return Response.ok().setPayload(serviceService.SelectEdit(filter));
    }


    // @PreAuthorize("hasPermission('hasAccess','100504')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {

        serviceService.delete(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response getByid(@PathVariable Long id) {
        return Response.ok().setPayload(serviceService.findById(id));
    }

    // @PreAuthorize("hasPermission('hasAccess','100503')")
    @PutMapping
    public Response edit(@Valid @RequestBody ServiceDto request) {
        log.warn(request.toJson());
        ServiceDto response = serviceService.edit(request);
        return Response.ok().setPayload(response);
    }


    // @PreAuthorize("hasPermission('hasAccess','100502')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {

        log.warn(Entity);
        List<CreateServiceExcelRequest> createServiceExcelRequests = (List<CreateServiceExcelRequest>) convertExcelService.ConvertExcelToObjects(CreateServiceExcelRequest.class, excel);
        if (serviceService.ExcelValidation(createServiceExcelRequests)) {
            List<ServiceDto> serviceDto = serviceService.ImportExcel(createServiceExcelRequests);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service", serviceDto.size());
            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.ok();
        }
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        List<SelectResponse> response = serviceService.select(filter);
        return Response.ok().setPayload(response);
    }

    @GetMapping("/serviceType/{id}")
    public Response getServiceType(@PathVariable Long id) {
        ServiceType byValue = ServiceType.findByValue(id);
        if (byValue != null)
            return Response.ok().setPayload(byValue);
        else
            throw BusinessException.entityNotFoundException(EntityType.Service, "service.type.not.found");
    }

    @GetMapping("/basetypeselect")
    public Response baseTypeSelect() {
        return Response.ok().setPayload(serviceService.baseTypeSelect());
    }


    @GetMapping("/additionaltypeselect")
    public Response additionalTypeSelect() {
        return Response.ok().setPayload(serviceService.additionalTypeSelect());
    }


}
