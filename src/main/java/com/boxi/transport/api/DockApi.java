package com.boxi.transport.api;
//100808	dock	DockApi	حمل و نقل - بارانداز

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.payload.dto.DockDto;
import com.boxi.transport.payload.dto.DockExcelDto;
import com.boxi.transport.payload.dto.DockFilter;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.service.DockService;
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

@RestController
@RequestMapping("/core-api/dock")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DockApi {

    private final DockService _service;
    private final ConvertExcelServiceImpl convertExcelService;

    // @PreAuthorize("hasPermission('hasAccess','10080801')")
    @PostMapping
    public Response create(@Valid @RequestBody DockDto request) {
        log.warn(request.toJson());
        DockDto response = _service.create(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080803')")
    @PutMapping
    public Response edit(@Valid @RequestBody DockDto request) {
        log.warn(request.toJson());
        DockDto response = _service.edit(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080804')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable Long id) {

        return Response.ok().setPayload(_service.findByIdDto(id));
    }

    @GetMapping("/external/{id}")
    public DockDto findByIdInClient(@PathVariable Long id) {
        return _service.findByIdDto(id);
    }

    @GetMapping("/external/{code}")
    public DockDto findByCodeInClient(@PathVariable String code) {
        return _service.findByCodeInClient(code);
    }

    @GetMapping("/external/findByHubId/{hubId}")
    public List<DockDto> externalFindByHubId(@PathVariable Long hubId) {
        return _service.externalFindByHubId(hubId);
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody DockFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<DockDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }


    @PostMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter,
                           @RequestBody HubFilter hubFilter) {
        Page<SelectResponse> response = _service.select(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080802')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {

        log.warn(Entity);
        List<DockExcelDto> dockExcelList =
                (List<DockExcelDto>) convertExcelService.ConvertExcelToObjects(DockExcelDto.class, excel);

        if (_service.ExcelValidation(dockExcelList)) {

            List<DockDto> dockList = _service.ImportExcel(dockExcelList);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dock", dockList.size());

            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.ok();
        }
    }

    @PostMapping("/update")
    public Response createByExcel(@RequestBody DockDto dto) {
        return Response.ok().setPayload(_service.updateStatus(dto));
    }
}
