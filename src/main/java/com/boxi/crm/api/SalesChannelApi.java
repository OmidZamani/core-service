package com.boxi.crm.api;

import com.boxi.core.request.GenericFilter;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SalesChannelDto;
import com.boxi.crm.payload.request.CustomerSegmentFilter;
import com.boxi.crm.service.SalesChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core-api/saleschannel")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class SalesChannelApi {
    private final SalesChannelService salesChannelService;


    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter"  ) String filter) {
        List<SelectResponse> response = salesChannelService.SelectChannel(filter);
        return  Response.ok().setPayload(response);
    }

    @PostMapping
    public Response create(@RequestBody SalesChannelDto dto){
        log.warn(dto.toJson());
        return  Response.ok().setPayload(salesChannelService.create(dto));

    }
    @PutMapping
    public Response edit(@RequestBody SalesChannelDto dto){
        log.warn(dto.toJson());
        return  Response.ok().setPayload(salesChannelService.edit(dto));

    }
    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody GenericFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        return Response.ok().setPayload( salesChannelService.filter(request, pageable));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id){
        salesChannelService.delete(id);
        return Response.ok();
    }

}
