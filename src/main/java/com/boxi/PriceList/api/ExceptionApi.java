package com.boxi.PriceList.api;
//100806	exception	ExceptionApi	حمل و نقل - استثناء
import com.boxi.PriceList.Enum.ExceptionType;
import com.boxi.PriceList.payload.dto.*;
import com.boxi.PriceList.payload.request.FilterException;
import com.boxi.PriceList.service.ExceptionService;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping( "/core-api/exception")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ExceptionApi {
    private final ExceptionService exceptionService ;

    private final ConvertExcelServiceImpl convertExcelService;

    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());
    }


    @GetMapping("/exceptiontype")
    public Response ExType() {
        return  Response.ok().setPayload(ExceptionType.select());
    }

    // @PreAuthorize("hasPermission('hasAccess','10080601')")
    @PostMapping
    public Response create(@RequestBody ExceptionDto request) {
        log.warn(request.toJson());

        ExceptionDto exceptionDto = exceptionService.create(request);
        return  Response.ok().setPayload(exceptionDto);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterException request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<ExceptionDto> response = exceptionService.filter(request, pageable);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','0')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        exceptionService.delete(id);
        return  Response.ok();
    }
    // @PreAuthorize("hasPermission('hasAccess','10080603')")
    @PutMapping
    public Response edit(@Valid @RequestBody ExceptionDto request) {
        log.warn(request.toString());
        ExceptionDto edit = exceptionService.edit(request);

        return  Response.ok().setPayload(edit);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080602')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {

        log.warn(Entity);
        List<ExceptionExcelDto> exceptionExcelList = (List<ExceptionExcelDto>) convertExcelService.ConvertExcelToObjects(ExceptionExcelDto.class, excel);
        if (exceptionService.ExcelValidation(exceptionExcelList)) {

            List<ExceptionDto> exceptionList = exceptionService.ImportExcel(exceptionExcelList);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Exception", exceptionList.size());

            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.ok();
        }
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter", required = false) String filter) {
        return Response.ok().setPayload(exceptionService.select(filter));
    }
    @GetMapping("/selectByType")
    public Response selectByType(@RequestParam(name = "filter", required = false) String filter,@RequestParam(name = "type", required = false) Long type) {
        return Response.ok().setPayload(exceptionService.selectByType(filter,type));
    }


    @GetMapping("/clientSelectByType")
    public List<SelectResponse> clientSelectByType(@RequestParam(name = "filter", required = false) String filter, @RequestParam(name = "type", required = false) Long type) {
        return exceptionService.selectByType(filter,type);
    }

}
