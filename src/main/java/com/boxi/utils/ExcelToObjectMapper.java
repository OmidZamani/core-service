package com.boxi.utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class ExcelToObjectMapper {

    private Workbook workbook;
    public  ExcelToObjectMapper(InputStream in) throws Exception {
        this.workbook=null;
        this.workbook= WorkbookFactory.create(in);
    }



    public synchronized  <T> ArrayList<T> map(Class<T> cls) throws Exception {
        ArrayList<T> list = new ArrayList();

        Sheet sheet = workbook.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        for (int i=1; i<=lastRow;i++) {
            Object obj = cls.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field: fields) {
                String fieldName = field.getName();
                int index = getHeaderIndex(fieldName, workbook);
                Cell cell = sheet.getRow(i).getCell(index);
                Field classField = obj.getClass().getDeclaredField(fieldName);
                setObjectFieldValueFromCell(obj, classField, cell);
            }
            list.add( (T) obj);
        }
        return list;
    }


    private synchronized  void setObjectFieldValueFromCell(Object obj, Field field, Cell cell){
        Class<?> clazz = field.getType();
        field.setAccessible(true);
        if(clazz == String.class) {
            try {
                field.set(obj, cell.getStringCellValue());
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }
        else if(clazz == Date.class) {
            try {
                Date date = cell.getDateCellValue();
                field.set(obj, date);
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }
        else if(clazz == int.class || clazz == float.class || clazz == double.class ){
            double value = cell.getNumericCellValue();
            try {
                if (clazz == int.class) {
                    field.set(obj, (int) value);
                }
                else if (clazz == float.class) {
                    field.set(obj, (float) value);
                }
                else {
                    //Double value
                    field.set(obj, value);
                }
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }

        else if(clazz == Long.class || clazz == long.class ){
            long value =(long) cell.getNumericCellValue();
            try {
              field.set(obj, value);
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }
        else if(clazz == Boolean.class) {
            boolean value = cell.getBooleanCellValue();
            try {
                field.set(obj, value);
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }


        }
        /*else if(cls == Collection.class) {
            double value = cell.getNumericCellValue();
            try {
                field.set(obj, value);
            }catch (Exception e) {
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        }*/
        else {
            // Unsupported data type.
        }

    }


    private synchronized  int getHeaderIndex(String headerName, Workbook workbook) throws Exception {
        Sheet sheet = workbook.getSheetAt(0);
        int totalColumns = sheet.getRow(0).getLastCellNum();
        int index = -1;
        for (index=0; index<totalColumns;index++) {
            Cell cell = sheet.getRow(0).getCell(index);
            if(cell.getStringCellValue().equals(headerName)) {
                break;
            }
        }
        if(index == -1) {
            throw new Exception("Invalid field name.");
        }
        return index;
    }
}

