package com.boxi.transport.api;
//100807	bagging	BagApi	حمل و نقل - کیسه بندی

import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import com.boxi.transport.enums.BagType;
import com.boxi.transport.payload.dto.*;
import com.boxi.transport.payload.request.HubFilter;
import com.boxi.transport.service.BagService;
import com.boxi.utils.BarCodeGeneration;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/core-api/bag")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BagApi {

    private final BagService _service;

    private final ConvertExcelServiceImpl convertExcelService;

    // @PreAuthorize("hasPermission('hasAccess','10080701')")
    @PostMapping
    public Response create(@Valid @RequestBody BagDto request) {
        log.warn(request.toJson());
        BagDto response = _service.create(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080703')")
    @PutMapping
    public Response edit(@Valid @RequestBody BagDto request) {//TODO Transactional
        log.warn(request.toJson());
        BagDto response = _service.edit(request);
        return Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10080704')")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        _service.delete(id);
        return Response.ok();
    }

    @PutMapping("/changestatus")
    public Response changeStatus(@RequestBody BagDto dto){
        return Response.ok().setPayload(_service.editStatus( dto));
    }


    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody BagFilter request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<BagDto> response = _service.filter(request, pageable);
        return Response.ok().setPayload(response);
    }


    @PostMapping("/select")
    public Response select(@RequestParam(name = "filter" ) String filter,
                           @RequestBody HubFilter hubFilter) {
        Page<SelectResponse> response = _service.select(filter, hubFilter);
        return Response.ok().setPayload(response);
    }

    @PostMapping(value = "/barcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode128(@RequestBody BarCodeDto dto)  {
        BufferedImage image = BarCodeGeneration.generateCode128BarcodeImage(dto);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/selectBagTypes")
    public Response selectBagTypes() {
        return Response.ok().setPayload(BagType.select());
    }

    // @PreAuthorize("hasPermission('hasAccess','10080702')")
    @PostMapping("/importexcelfile")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity) throws IOException {
        log.warn(Entity);

        List<BagExcelDto> bagExcelList =
                (List<BagExcelDto>) convertExcelService.ConvertExcelToObjects(BagExcelDto.class, excel);

        if (_service.ExcelValidation(bagExcelList)) {
            List<BagDto> bagList = _service.ImportExcel(bagExcelList);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Bag", bagList.size());
            return Response.ok().setPayload(jsonObject);
        } else {
            return Response.exception();
        }
    }

    @GetMapping("/findbyid/{id}")
    public BagDto findBybagnumber(@PathVariable Long id) {
        return _service.findByIdDto(id);
    }

    @GetMapping("/findbynumber/{number}")
    public BagDto findBybagnumber(@PathVariable String number) {

        return _service.findByBagNumber(number);
    }

    @PostMapping("/createException")
    public Response createException(@RequestBody BagExceptionsDto dto) {
        return Response.ok().setPayload(_service.createException(dto));
    }

    @GetMapping("/reportTotal/{hubid}")
    public Response reportTotal(@PathVariable Long hubid) {
        return Response.ok().setPayload(_service.reportTotal(hubid));
    }
}
