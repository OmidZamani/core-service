package com.boxi.hub.api;

import com.boxi.core.response.Response;
import com.boxi.hub.payload.dto.PudoStationDto;
import com.boxi.hub.payload.dto.PudoStationRequest;
import com.boxi.hub.service.PudoStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/core-api/pudoStation")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PudoStationApi {
    private final PudoStationService pudoStationService;

    public PudoStationApi(PudoStationService PudoStationService) {
        this.pudoStationService = PudoStationService;
    }

    @PostMapping
    public Response create(@RequestBody PudoStationDto dto) {
        return Response.ok().setPayload(pudoStationService.create(dto));
    }

    @PutMapping
    public Response edit(@RequestBody PudoStationDto dto) {
        return Response.ok().setPayload(pudoStationService.edit(dto));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        pudoStationService.delete(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable Long id) {
        return Response.ok().setPayload(pudoStationService.findById(id));
    }

    @GetMapping("/client/{id}")
    public PudoStationDto clientFindById(@PathVariable Long id) {
        return pudoStationService.findById(id);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody PudoStationDto request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(pudoStationService.filter(request, pageable));
    }

    @GetMapping("/listOfStation")
    public List<PudoStationDto> listOfStation() {
        return pudoStationService.listOfStation();
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        return Response.ok().setPayload(pudoStationService.select(filter));
    }

    @PostMapping("/list-unused-stations")
    public List<PudoStationDto> getUnusedPudoStations(@RequestBody PudoStationRequest request) {
        List<Long> pudostationIds = request.getPudostationIds();
        Long hubId = request.getHubId();
        return pudoStationService.getUnusedPudoStations(pudostationIds, hubId);
    }
    @PostMapping("/findByIdList/{hubId}")
    public List<PudoStationDto> findByIdList(@RequestBody List<Long> list, @PathVariable Long hubId){
        return pudoStationService.findByIdList(list,hubId);
    }

    @PostMapping("/findByNotIdList/{hubId}")
    public List<PudoStationDto> findByNotIdList(@RequestBody List<Long> list, @PathVariable Long hubId){
        return pudoStationService.findByNotIdList(list,hubId);
    }


    @PostMapping("/findStationByHubId/{hubId}")
    public List<PudoStationDto> findStationByHubId( @PathVariable Long hubId){
        return pudoStationService.findStationByHubId(hubId);
    }

}
