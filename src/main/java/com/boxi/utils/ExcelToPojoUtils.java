package com.boxi.utils;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class ExcelToPojoUtils {
    public static final String BOOLEAN_TRUE = "TRUE";
    public static final String LIST_SEPARATOR = ",";

    private static String strToFieldName(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        return str.length() > 0 ? str.substring(0, 1).toLowerCase() + str.substring(1) : null;
    }

    public static int findColSheet(Workbook workbook, String ColName) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            if (workbook.getSheetName(i).equals(ColName)) {
                return i;
            }
        }
        return 0;

    }

    public static <T> List<T> toPojo(Class<T> type, InputStream inputStream) throws IOException {
        List<T> results = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // header columns
        List<String> colNames = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            Cell headerCell = headerRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            colNames.add(headerCell != null ? type.getDeclaredFields()[i].getName() : null);

        }

        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            String CodeName = "";
            try {
                T result = type.getDeclaredConstructor().newInstance();
                for (int k = 0; k < colNames.size(); k++) {
                    try {
//                        if (colNames.get(k) != null) {

                            Cell cell = row.getCell(k, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
//                            if (cell != null) {
                                DataFormatter formatter = new DataFormatter();
                                String strValue = formatter.formatCellValue(cell);
                                Field field = type.getDeclaredField(colNames.get(k));

                                field.setAccessible(true);
                                if (field != null) {
                                    Object value = null;
                                    if (field.getType().equals(Boolean.class)) {
                                        if (strValue.equals("فعال")) value = true;
                                        else if (strValue.trim().equals("غیر فعال"))  value = false;
                                        else if (strValue.trim().equals("غیرفعال"))  value = false;
                                        else if (strValue.trim().equals("بله"))  value = true;
                                        else if (strValue.equals("خیر"))  value = false;



                                    }
                                    else if (field.getType().equals(BigDecimal.class)) {
                                        value = BigDecimal.valueOf(Long.parseLong(strValue));
                                    }else if (field.getType().equals(Double.class)) {
                                        value = Double.valueOf(strValue);
                                    }
                                    else if (field.getType().equals(Long.class)) {
                                        value = Long.valueOf(strValue);
                                    } else if (field.getType().equals(String.class)) {
                                        value = strValue;
                                    } else {
                                        Class aClass = field.getType();
                                        if (aClass.isEnum()) {
                                            Object val = Enum.valueOf(aClass, strValue);
                                            value = val;
                                        }
                                    }
                                    CodeName = strValue;
                                    int colSheet = findColSheet(workbook, field.getName());
                                    if (colSheet != 0) {
                                        Class aClass = field.getType();
                                        if (aClass.isLocalClass()) {
                                            log.warn("isClass -> " + aClass.getName());
                                        }
                                        Cell Mycell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                                        ParameterizedType FindListType = (ParameterizedType) field.getGenericType();
                                        Class<?> FindListClass = (Class<?>) FindListType.getActualTypeArguments()[0];
                                        List<?> objects = toPojoDetails(FindListClass, workbook.getSheetAt(colSheet), colSheet, formatter.formatCellValue(Mycell));
                                        if(objects.size()!=0) {
                                            field.set(result, objects);
                                            CodeName = "Class";
                                        }
                                    } else {
                                        field.set(result, value);
                                    }
//                                }
//                            }
                        }
                    } catch (Exception e) {


                    }
                }

                if (CodeName != null && StringUtils.isNotBlank(CodeName))
                    results.add(result);
            } catch (Exception e) {
                e.printStackTrace();
                throw BusinessException.throwException(EntityType.Excel, ExceptionType.VALUE_EXCEPTION, e.getMessage());
            }
        }

        return results;
    }

    public static <T> List<T> toPojoDetails(Class<T> type, Sheet sheet, int SheetId, String ParentCode) throws IOException {
        List<T> results = new ArrayList<>();


        // header columns
        List<String> colNames = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            Cell headerCell = headerRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            colNames.add(headerCell != null ? type.getDeclaredFields()[i].getName() : null);

        }

        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            String CodeName = "";
            try {
                T result = type.getDeclaredConstructor().newInstance();
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    try {
                        Cell Mycell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        DataFormatter Myformatter = new DataFormatter();

                        if (!Myformatter.formatCellValue(Mycell).equals(ParentCode) || Myformatter.formatCellValue(Mycell) == null)        break;
                        if (colNames.get(k) != null) {
                            Cell cell = row.getCell(k, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                            if (cell != null) {
                                DataFormatter formatter = new DataFormatter();
                                String strValue = formatter.formatCellValue(cell);
                                Field field = type.getDeclaredField(colNames.get(k));

                                field.setAccessible(true);
                                if (field != null) {
                                    Object value = null;
                                    if (field.getType().equals(Boolean.class)) {
                                        if (strValue.equals("فعال")) value = true;
                                        else if (strValue.trim().equals("غیر فعال"))  value = false;
                                        else if (strValue.trim().equals("غیرفعال"))  value = false;
                                        else if (strValue.trim().equals("بله"))  value = true;
                                        else if (strValue.equals("خیر"))  value = false;

                                    }else if (field.getType().equals(BigDecimal.class)) {
                                        value = BigDecimal.valueOf(Long.parseLong(strValue));
                                    }
                                    else if (field.getType().equals(Double.class)) {
                                        value = Double.valueOf(strValue);
                                    } else if (field.getType().equals(Long.class)) {
                                        value = Long.valueOf(strValue);
                                    } else if (field.getType().equals(String.class)) {
                                        value = strValue;
                                    } else {
                                        Class aClass = field.getType();
                                        if (aClass.isEnum()) {
                                            @SuppressWarnings("unchecked")
                                            Object val = Enum.valueOf(aClass, strValue);
                                            value = val;
                                        }
                                    }

                                    CodeName = strValue;
                                    field.set(result, value);
                                }
                            }
                        }
                    } catch (Exception e) {

                    }

                }
                if (CodeName != null && StringUtils.isNotBlank(CodeName))
                    results.add(result);
            } catch (Exception e) {
                e.printStackTrace();
                throw BusinessException.throwException(EntityType.Hub, ExceptionType.VALUE_EXCEPTION, e.getMessage());
            }
        }

        return results;
    }
}