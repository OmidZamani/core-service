package com.boxi.excel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ConvertExcelService {

    void ConvertToExcel(MultipartFile file , String Dto) throws IOException;

    Object ConvertExcelToObjects(Class ExceDtoName,MultipartFile file)throws IOException;
}
