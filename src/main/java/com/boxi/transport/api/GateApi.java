package com.boxi.transport.api;
//100809	gate	GateApi	حمل و نقل - درب

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.service.GateService;
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

@RestController
@RequestMapping("/core-api/gate")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GateApi {

    private final GateService _service;
    private final ConvertExcelServiceImpl convertExcelService;

    // @PreAuthorize("hasPermission('hasAccess','10080901')")
    @PostMapping
    public Response create(@Valid @RequestBody GateDto request) {
        log.warn(request.toJson());
        GateDto response = _service.create(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080903')")
    @PutMapping
    public Response edit(@Valid @RequestBody GateDto request) {
        log.warn(request.toJson());
        GateDto response = _service.edit(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080904')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return Response.ok();
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody GateFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<GateDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }


    @PostMapping("/select")
    public Response select(@RequestParam(name = "filter", required = true) String filter,
                           @RequestBody HubFilter hubFilter) {
        Page<SelectResponse> response = _service.select(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080902')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        List<GateExcelDto> gateExcelDtos =
                (List<GateExcelDto>) convertExcelService.ConvertExcelToObjects(GateExcelDto.class, excel);

        if (_service.ExcelValidation(gateExcelDtos)) {

            List<GateDto> bagDtos = _service.ImportExcel(gateExcelDtos);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Gate", bagDtos.size());
            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.exception();
        }
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable Long id) {
        return Response.ok().setPayload(_service.findByIddto(id));
    }
}
