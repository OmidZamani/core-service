package com.boxi.PriceList.api;

import com.boxi.PriceList.payload.dto.TypesOfPackageDto;
import com.boxi.PriceList.payload.request.FilterServiceDelivery;
import com.boxi.PriceList.service.TypesOfPackageService;
import com.boxi.core.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/core-api/typesOfPackage")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class TypesOfPackageApi {

    private final TypesOfPackageService typesOfPackageService;

    @PostMapping()
    public Response create(@RequestBody TypesOfPackageDto dto) {
        return Response.ok().setPayload(typesOfPackageService.create(dto));
    }

    @PutMapping()
    public Response edit(@RequestBody TypesOfPackageDto dto) {
        return Response.ok().setPayload(typesOfPackageService.create(dto));
    }

    @GetMapping("/{id}")
    public Response edit(@PathVariable Long id) {
        return Response.ok().setPayload(typesOfPackageService.findById(id));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        typesOfPackageService.delete(id);
        return Response.ok();
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody TypesOfPackageDto request) {
        Pageable pageable
                = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(typesOfPackageService.filter(request, pageable));

    }

}
