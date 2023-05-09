package com.boxi.excel.service.impl;

import com.boxi.excel.payload.CreateProductExcelRequest;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.excel.service.ConvertExcelService;
import com.boxi.hub.payload.dto.HubDto;
import com.boxi.transport.payload.excel.CreateHubExcelRequest;
import com.boxi.utils.ExcelToPojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ConvertExcelServiceImpl implements ConvertExcelService {

    @Override
    public void ConvertToExcel(MultipartFile file, String Dto) throws IOException {

        List<CreateServiceExcelRequest> createServiceExcelRequests = (List<CreateServiceExcelRequest>) ConvertExcelToObjects(CreateServiceExcelRequest.class, file);
        for (CreateServiceExcelRequest createServiceExcelRequest : createServiceExcelRequests) {
            log.warn(createServiceExcelRequest.toJson());
        }


    }

    public Object ConvertExcelToObjects(Class ExceDtoName,MultipartFile file) throws IOException {
         List<CreateServiceExcelRequest> Dto = ExcelToPojoUtils.toPojo(ExceDtoName, file.getInputStream());
         return Dto;
    }
}
