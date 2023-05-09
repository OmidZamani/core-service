package com.boxi.transport.api;
//100804	route	RouteApi	حمل و نقل - مسیر
import com.boxi.core.request.RouteFilter;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.payload.dto.RouteExcelDto;
import com.boxi.transport.service.RouteService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/core-api/route")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RouteApi {

    private final RouteService _service;
    private final ConvertExcelServiceImpl convertExcelService;

    // @PreAuthorize("hasPermission('hasAccess','10080401')")
    @PostMapping
    public Response create(@Valid @RequestBody RouteDto request) {
        log.warn(request.toJson());
        RouteDto response=_service.create(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080403')")
    @PutMapping
    public Response edit(@Valid @RequestBody RouteDto request) {
        log.warn(request.toJson());
        RouteDto response=_service.edit(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080404')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return  Response.ok();
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return  Response.ok().setPayload( _service.get(id));
    }




    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody RouteFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<RouteDto> response = _service.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }


    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = _service.select(filter);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/selectRoute")
    public List<RouteDto> selectRoute(@RequestParam(name = "source",required = true) Long source,
                              @RequestParam(name = "destination",required = true) Long destination) {
        List<RouteDto> routeDtos = _service.selectRoute(source, destination).stream().collect(Collectors.toList());
        return  routeDtos;
    }
    // @PreAuthorize("hasPermission('hasAccess','10080402')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        List<RouteExcelDto> routeExcelDtos =
                (List<RouteExcelDto>) convertExcelService.ConvertExcelToObjects(RouteExcelDto.class, excel);

        if (_service.ExcelValidation(routeExcelDtos)) {
            List<RouteDto> bagDtos = _service.ImportExcel(routeExcelDtos);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("route",bagDtos.size());
            return Response.ok().setPayload(jsonObject);
        }
        else {
            return Response.exception();
        }
    }
}
