package com.boxi.hub.api;
//1013	custom country division	CustomCountryDevisionApi	تعریف رده جغرافیایی سفارشی

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.hub.payload.dto.CustomCountryDevisionDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionExcelDto;
import com.boxi.hub.payload.dto.CustomCountryDevisionFilterDto;
import com.boxi.hub.payload.request.FilterCustomCountryDevision;
import com.boxi.hub.service.CustomCountryDevisionService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/core-api/customcountrydevision")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomCountryDevisionApi {

    private final CustomCountryDevisionService customCountryDevisionService;

    private final ConvertExcelServiceImpl convertExcelService;


    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }


    // @PreAuthorize("hasPermission('hasAccess','101301')")
    @PostMapping
    public Response create(@RequestBody CustomCountryDevisionDto request) {
        log.warn(request.toJson());
        CustomCountryDevisionDto customCountryDevisionDto = customCountryDevisionService.create(request);
        return Response.ok().setPayload(customCountryDevisionDto);
    }

    // @PreAuthorize("hasPermission('hasAccess','101303')")
    @PutMapping
    public Response edit(@Valid @RequestBody CustomCountryDevisionDto request) {
        log.warn(request.toJson());
        CustomCountryDevisionDto edit = customCountryDevisionService.edit(request);
        return Response.ok().setPayload(edit);
    }

    // @PreAuthorize("hasPermission('hasAccess','101304')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        customCountryDevisionService.delete(id);
        return Response.ok();
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterCustomCountryDevision request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<CustomCountryDevisionFilterDto> filter = customCountryDevisionService.filter(request, pageable);
        return Response.ok().setPayload(filter);
    }


    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter) {
        Page<SelectResponse> response = customCountryDevisionService.select(filter);
        return Response.ok().setPayload(response);
    }


    // @PreAuthorize("hasPermission('hasAccess','101302')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {
        log.warn(Entity);
        List<CustomCountryDevisionExcelDto> customCountryDivisionList =
                (List<CustomCountryDevisionExcelDto>) convertExcelService.ConvertExcelToObjects(CustomCountryDevisionExcelDto.class, excel);

        if (customCountryDevisionService.ExcelValidation(customCountryDivisionList)) {

            List<CustomCountryDevisionDto> customCountryDivisionLists = customCountryDevisionService.ImportExcel(customCountryDivisionList);

            int DetailsCount = 0;
            for (CustomCountryDevisionDto customCountryDevisionDto : customCountryDivisionLists) {

                DetailsCount += customCountryDevisionDto.getCustomDevisionDetails().size();
            }

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("customcountrydevision", customCountryDivisionLists.size());
            jsonObject.put("customDevisionDetails", DetailsCount);
            return Response.ok().setPayload(customCountryDivisionLists);
        } else {
            return Response.ok();
        }
    }

}
