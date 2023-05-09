package com.boxi.transport.api;

import com.boxi.core.response.Response;
import com.boxi.transport.payload.dto.ConnectionDelDto;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionFilter;
import com.boxi.transport.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/connection")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConnectionApi {

    @Autowired
    ConnectionService _service;


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody ConnectionFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ConnectionDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }


    @DeleteMapping()
    public Response delete(@RequestBody ConnectionDelDto dto) {
        log.warn(dto.toString());
        _service.physicalDelete(dto);
        return Response.ok();
    }


    @GetMapping("/{id}")
    public ConnectionDto findByid(@PathVariable Long id) {
        return _service.findByIdDto(id);
    }

}
