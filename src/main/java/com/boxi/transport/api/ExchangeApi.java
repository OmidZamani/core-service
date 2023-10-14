package com.boxi.transport.api;

import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.RouteDto;
import com.boxi.transport.service.ConnectionService;
import com.boxi.transport.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/core-api/externalRoute")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ExchangeApi {
    private final RouteService _service;
    private final ConnectionService connectionService;

    @GetMapping("/{id}")
    public RouteDto getById(@PathVariable Long id) {
        return _service.get(id);
    }

    @GetMapping("/selectRoute")
    public List<RouteDto> selectRoute(@RequestParam(name = "source" ) Long source,
                                      @RequestParam(name = "destination" ) Long destination) {

        return _service.selectRoute(source, destination).stream().collect(Collectors.toList());
    }

    @GetMapping("/connection/{id}")
    public ConnectionDto findByid(@PathVariable Long id) {
        return connectionService.findByIdDto(id);
    }

    @GetMapping("/route/{id}")
    public RouteDto getRouteByID(@PathVariable Long id){
        return _service.get(id);
    }
}
