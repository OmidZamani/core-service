package com.boxi.transport.api;
//100801	vendor	VendorApi	حمل و نقل - شرکت های نقلیه
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.core.request.GenericFilter;
import com.boxi.transport.payload.dto.VendorExcelDto;
import com.boxi.transport.service.VendorService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/core-api/vendor")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class VendorApi {

    private final VendorService vendorService;

    private final ConvertExcelServiceImpl convertExcelService;

    @Autowired
    public VendorApi(VendorService vendorService, ConvertExcelServiceImpl convertExcelService) {
        this.vendorService = vendorService;
        this.convertExcelService = convertExcelService;
    }

    // @PreAuthorize("hasPermission('hasAccess','10080101')")
    @PostMapping
    public Response create(@RequestBody @Valid VendorDto request) {
        log.warn(request.toJson());
        VendorDto response=vendorService.createVendor(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080103')")
    @PutMapping
    public Response edit(@RequestBody @Valid VendorDto request) {
        log.warn(request.toJson());
        VendorDto response=vendorService.updateVendor(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080104')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        vendorService.delete(id);
        return  Response.ok();
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody GenericFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<VendorDto> response = vendorService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = vendorService.select(filter);
        return  Response.ok().setPayload(response);
    }

//    @PostMapping("/createHubsByExcel")
//    public Response createByExcel(@RequestParam("file") MultipartFile excel) {
//        vendorService.createByExcel(excel);
//        return  Response.ok();
//    }

    // @PreAuthorize("hasPermission('hasAccess','10080102')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);

        List<VendorExcelDto> vendorExcelDtos =
                (List<VendorExcelDto>) convertExcelService.ConvertExcelToObjects(VendorExcelDto.class, excel);

        if (vendorService.ExcelValidation(vendorExcelDtos)) {

            List<VendorDto> vendorExcelDtos1 = vendorService.ImportExcel(vendorExcelDtos);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("vendor",vendorExcelDtos1.size());
            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.exception();
        }
    }

}
