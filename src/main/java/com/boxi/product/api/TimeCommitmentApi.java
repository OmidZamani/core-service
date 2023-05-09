package com.boxi.product.api;
//1012	time commitment	TimeCommitmentApi	تعریف مدت ارائه خدمت
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import com.boxi.product.payload.request.FilterTimeCommitment;
import com.boxi.product.service.TimeCommitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/timecommitment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class TimeCommitmentApi {
   private final TimeCommitmentService timeCommitmentService;
    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return  Response.ok().setPayload(UUID.randomUUID().toString());

    }


    @GetMapping("/selectTimeUnitTypes")
    public Response hybTypes() {
        return  Response.ok().setPayload(TimeUnit.select());
    }

    // @PreAuthorize("hasPermission('hasAccess','101201')")
    @PostMapping
    public Response create(@RequestBody TimeCommitmentDto request) {

        log.warn(request.toJson());
        TimeCommitmentDto response=timeCommitmentService.create(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','101202')")
    @PutMapping
    public Response edit(@Valid @RequestBody TimeCommitmentDto request) {
        log.warn(request.toJson());
        TimeCommitmentDto response=timeCommitmentService.edit(request);
        return  Response.ok().setPayload(response);
    }
    // @PreAuthorize("hasPermission('hasAccess','101203')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        timeCommitmentService.delete(id);
        return  Response.ok();
    }
    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize,
                           @RequestBody FilterTimeCommitment request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        Page<TimeCommitmentDto> response = timeCommitmentService.filter(request,pageable);
        return  Response.ok().setPayload(response);
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter",required = true) String filter) {
        Page<SelectResponse> response = timeCommitmentService.select(filter);
        return  Response.ok().setPayload(response);
    }

}
