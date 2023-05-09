package com.boxi.crm.api;

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import com.boxi.crm.payload.request.CustomerSegmentFilter;
import com.boxi.crm.service.CustomerSegmentService;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.hub.payload.request.FilterHub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core-api/customersegment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CustomerSegmentApi {
    private final CustomerSegmentService customerSegmentService;


    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter", required = true) String filter) {
        List<SelectResponse> response = customerSegmentService.Select(filter);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody CustomerSegmentFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<CustomerSegmentDto> response = customerSegmentService.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @PostMapping
    public Response create(@RequestBody CustomerSegmentDto customerSegmentDto) {
        log.warn(customerSegmentDto.toJson());
        CustomerSegmentDto dto = customerSegmentService.create(customerSegmentDto);
        return Response.ok().setPayload(dto);
    }
    @PutMapping
    public Response edit(@RequestBody CustomerSegmentDto customerSegmentDto) {
        log.warn(customerSegmentDto.toJson());
        CustomerSegmentDto dto = customerSegmentService.edit(customerSegmentDto);
        return Response.ok().setPayload(dto);
    }

    @GetMapping("/{id}")
    public Response findbyid(@PathVariable Long id) {
        return Response.ok().setPayload(customerSegmentService.findedit(id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        customerSegmentService.delete(id);
        return Response.ok();

    }

}
