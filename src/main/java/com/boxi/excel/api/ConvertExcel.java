package com.boxi.excel.api;

import com.boxi.core.response.Response;
import com.boxi.excel.service.impl.ConvertExcelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/core-api/excel")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class ConvertExcel {

    private final ConvertExcelServiceImpl convertExcelService;
    @PostMapping("/addtoExcel")
    public Response createByExcel(@RequestParam("file") MultipartFile excel, @RequestParam("Entity") String Entity, HttpServletRequest request) throws IOException {
        String contextPath = request.getRequestURI();
        log.warn(Entity);
        String Dto = Entity;
        convertExcelService.ConvertToExcel(excel,Dto);
        return  Response.ok();
    }
}
