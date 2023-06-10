package com.boxi.hub.api;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.payload.dto.CustomDevisionDetailDto;
import com.boxi.hub.payload.request.FilterCustomDevisionDetail;
import com.boxi.hub.service.CustomDevisionDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/core-api/customDevisiondetail")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class CustomDevisionDetailApi {

    private final CustomDevisionDetailService customDevisionDetailService;

    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }

    @PostMapping
    public Response create(@RequestBody CustomDevisionDetailDto request) {
        log.warn(request.toJson());
        return  Response.ok().setPayload(customDevisionDetailService.create(request));
    }

    @PutMapping
    public Response edit(@Valid @RequestBody List<CustomDevisionDetailDto> request) {
        log.warn(request.toString());
        CustomDevisionDetailDto edit = customDevisionDetailService.edit(null);
        return  Response.ok().setPayload(edit);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        customDevisionDetailService.delete(id);
        return  Response.ok();
    }
    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterCustomDevisionDetail request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<CustomDevisionDetailDto> response = customDevisionDetailService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        Page<SelectResponse> response = customDevisionDetailService.select(filter);
        return  Response.ok().setPayload(response);
    }

}
