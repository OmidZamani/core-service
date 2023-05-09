package com.boxi.trm.service;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.trm.entity.DispatchShift;
import com.boxi.trm.payload.dto.*;
import com.boxi.trm.payload.request.FilterCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CalendarService {

    CalendarHubDto createcalendarhub(CalendarHubDto dto) ;

    CalendarHubDto editcalendarhub(CalendarHubDto dto) ;

    List<DispatchShiftDto> create(List<DispatchShiftDto> dto);

    DispatchShiftDto edit(DispatchShiftDto dto);

    void delete(List<SelectResponse> ids);

    List<CalendarDto> filter(FilterCalendar filter, Pageable pageable);

    Page<CalendarHubDto> filtercalendarhub(FilterCalendar filter, Pageable pageable);

   List<DispatchShiftDto> findbyDispatchershift(SelectResponse calendar);

    CopyDispatchShiftDto copyDispatchShift(CopyDispatchShiftDto shiftDtos);

    void deleteselectshift(List<DispatchShiftDto> shiftDtos);

    String  createnormalshift(NormalShiftDto dto);// تعریف شیفت ساده  تکرار شونده

    void copyyearhubbyweekday(CopyYearHubDto dto);

    void copyyearhubbyday(CopyYearHubDto dto);

    List<SelectResponse> getyears();

    Page<CalendarHubDto> filtertrm(FilterCalendar filter, Pageable pageable);

     DispatchShiftDto createDispatcher(DispatchShiftDto dto);
     void deletecalenderhub(Long id );

    DispatchShiftDto findshiftbyid(Long id);

    DispatchShiftDto findnextshiftbyid(Long id);

    List<DispatchShiftDto> findbyDispatchershiftByDate(DateDto calendar,Long hubid);

    List<SelectResponse> findShiftBytype(Long type, DateDto dateDto,Long hubid);

    List<DispatchShiftDto> getfindbycalendardisByDateByhub(FindByDispatcherByHubDto dto);
}
