package com.boxi.hub.api;

import com.boxi.core.response.Response;
import com.boxi.hub.payload.dto.SharePercentageDto;
import com.boxi.hub.service.SharePercentageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/core-api/sharePercentage")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SharePercentageApi {
    private final SharePercentageService sharePercentageService;

    @PostMapping("/")
    private Response<Object> create(@RequestBody SharePercentageDto dto) {
        return Response.ok().setPayload(sharePercentageService.create(dto));

    }

    @PutMapping("/")
    private Response<Object> edit(@RequestBody SharePercentageDto dto) {
        return Response.ok().setPayload(sharePercentageService.edit(dto));
    }

    @GetMapping("/{id}")
    private Response<Object> findById(@PathVariable Long id) {
        return Response.ok().setPayload(sharePercentageService.findById(id));
    }

    @GetMapping("/external/{id}")
    private Response<Object> externalFindById(@PathVariable Long id) {
        return Response.ok().setPayload(sharePercentageService.findById(id));
    }

    @GetMapping("/external/fetchListOfPercentageByPrice/{price}")
    private List<SharePercentageDto> fetchListOfPercentageByPrice(@PathVariable BigDecimal price){
        return sharePercentageService.fetchListOfPercentageByPrice(price);
    }

}
