package com.boxi.PriceList.api;
//1006	serice delivery	ServiceDeliveryServiceApi	ارائه سرویس
import com.boxi.PriceList.Enum.DeliveryDiscountType;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.payload.request.FilterServiceDelivery;
import com.boxi.PriceList.service.ServiceDeliveryService;
import com.boxi.core.response.Response;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequestMapping("/core-api/servicedeliveryservice")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ServiceDeliveryServiceApi {

    private final ServiceDeliveryService serviceDeliveryService ;
    private final ConvertExcelServiceImpl convertExcelService;

    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());
    }

    // @PreAuthorize("hasPermission('hasAccess','100601')")
    @PostMapping
    public Response create(@RequestBody ServiceDeliveryDto request) {
        log.warn(request.toJson());
        request.setIsDeleted(false);
        ServiceDeliveryDto response=serviceDeliveryService.create(request);
        return  Response.ok().setPayload(response);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterServiceDelivery request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<ServiceDeliveryDto> response = serviceDeliveryService.filter(request, pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/selectbyedit")
    public Response selectByEdit(@RequestParam(name = "filter",required = true) String filter) {

        return  Response.ok().setPayload(serviceDeliveryService.SelectEdit(filter));
    }


    // @PreAuthorize("hasPermission('hasAccess','100604')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        serviceDeliveryService.delete(id);
        return  Response.ok();
    }

    // @PreAuthorize("hasPermission('hasAccess','100603')")
    @PutMapping
    public Response edit(@Valid @RequestBody ServiceDeliveryDto request) {
        log.warn(request.toJson());
        ServiceDeliveryDto response = serviceDeliveryService.edit(request);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/deliverydiscounttype")
    public Response DeliveryDiscountType() {
        return  Response.ok().setPayload(DeliveryDiscountType.select());
    }

    // @PreAuthorize("hasPermission('hasAccess','100602')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        List<ServiceDeliveryExcelDto> exceptionExcelDtos = (List<ServiceDeliveryExcelDto>)
                convertExcelService.ConvertExcelToObjects(ServiceDeliveryExcelDto.class, excel);
        if (serviceDeliveryService.ExcelValidation(exceptionExcelDtos)) {
            for (ServiceDeliveryExcelDto exceptionExcelDto : exceptionExcelDtos) {
                log.warn(exceptionExcelDto.toJson());
            }

            List<ServiceDeliveryDto> serviceDeliveryDtos = serviceDeliveryService.ImportExcel(exceptionExcelDtos);


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("service",serviceDeliveryDtos.size());
            int i = 1;
            for (ServiceDeliveryDto exceptionDto : serviceDeliveryDtos) {
                i+=exceptionDto.getCustomerSegments().size();
            }
            jsonObject.put("CustomerSegments",i);

            int b = 1;
            for (ServiceDeliveryDto exceptionDto : serviceDeliveryDtos) {
                b+=exceptionDto.getSaleschannels().size();
            }
            jsonObject.put("Saleschannels",b);

            int c = 1;
            for (ServiceDeliveryDto exceptionDto : serviceDeliveryDtos) {
                c+=exceptionDto.getDeliveryDiscounts().size();
            }
            jsonObject.put("DeliveryDiscounts",c);




            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.ok();
        }
    }

    // @PreAuthorize("hasPermission('hasAccess','100504')")
    @DeleteMapping("/deliverydiscount/{id}")
    public Response deleteDiscount(@PathVariable Long id) {

        serviceDeliveryService.deleteDiscount(id);
        return  Response.ok();
    }



}
