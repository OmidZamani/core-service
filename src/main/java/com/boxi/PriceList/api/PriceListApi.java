package com.boxi.PriceList.api;

import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.PriceListExcelDto;
import com.boxi.PriceList.payload.dto.PriceListFilterDto;
import com.boxi.PriceList.payload.dto.PriceListSuggestDto;
import com.boxi.PriceList.payload.request.FilterPriceList;
import com.boxi.PriceList.service.PriceListService;
import com.boxi.core.response.Response;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core-api/pricelist")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class PriceListApi {

    private final PriceListService priceListService;
    private final ConvertExcelServiceImpl convertExcelService;

    @GetMapping("/genPinCode")
    public Response genPinCode() {
        System.out.println("genPinCode");
        return Response.ok().setPayload(UUID.randomUUID().toString());
    }

    @PostMapping
    public Response create(@RequestBody PriceListDto request) {
        log.warn(request.toJson());
        request.setIsDeleted(false);
        PriceListDto priceListDto = priceListService.create(request);
        return Response.ok().setPayload(priceListDto);
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterPriceList request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<PriceListFilterDto> response = priceListService.filter(request, pageable);
        return Response.ok().setPayload(response);
    }

    @PostMapping("/suggets")
    public List<PriceListSuggestDto> suggest(@RequestBody FilterPriceList request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(0, 10);
        List<PriceListSuggestDto> response = priceListService.check(request, pageable);
        return response;
    }

    @GetMapping("/consignmenttype")
    public Response consignmentType() {
        return Response.ok().setPayload(priceListService.consignmentType());
    }

    @GetMapping("/selectbyedit")
    public Response selectByEdit(@RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(priceListService.SelectEdit(filter));
    }

    @GetMapping("/Se")
    public Response SampleGetAllDepandancy(@RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(priceListService.SE(filter));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {

        priceListService.Delete(id);


        return Response.ok();
    }

    @PutMapping
    public Response edit(@RequestBody PriceListDto request) {
        log.warn(request.toString());
        PriceListDto priceListDto = priceListService.edit(request);
        return Response.ok().setPayload(priceListDto);
    }

    @GetMapping("/select")
    public Response selectList(@RequestParam(name = "filter", required = true) String filter) {
        return Response.ok().setPayload(priceListService.SelectList());
    }


    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        List<PriceListExcelDto> priceListExcelDtos =
                (List<PriceListExcelDto>) convertExcelService.ConvertExcelToObjects(PriceListExcelDto.class, excel);

        if (priceListService.ExcelValidation(priceListExcelDtos)) {

            List<PriceListDto> priceListDtos = priceListService.ImportExcel(priceListExcelDtos);
            int Detailscount = 0;
            for (PriceListDto priceListDto : priceListDtos) {
                Detailscount += priceListDto.getPriceListDetails().size();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("priceList", priceListDtos.size());
            jsonObject.put("priceListDetails", Detailscount);
            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.exception();
        }
    }
}
