package com.boxi.trm.service;

import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.trm.payload.dto.*;
import com.boxi.trm.payload.request.FilterCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CalendarService {

    CalendarHubDto createCalendarHub(CalendarHubDto dto) ;

    CalendarHubDto editCalendarHub(CalendarHubDto dto) ;

    List<DispatchShiftDto> create(List<DispatchShiftDto> dto);

    DispatchShiftDto edit(DispatchShiftDto dto);

    void delete(List<SelectResponse> ids);

    List<CalendarDto> filter(FilterCalendar filter, Pageable pageable);

    Page<CalendarHubDto> filterCalendarHub(FilterCalendar filter, Pageable pageable);

   List<DispatchShiftDto> findByDispatcherShift(SelectResponse calendar);

    CopyDispatchShiftDto copyDispatchShift(CopyDispatchShiftDto shiftList);

    void deleteSelectShift(List<DispatchShiftDto> shiftList);

    String createNormalShift(NormalShiftDto dto);// تعریف شیفت ساده تکرار شونده

    void copyYearHubByWeekday(CopyYearHubDto dto);

    void copyYearHubByDay(CopyYearHubDto dto);

    List<SelectResponse> getYears();

    Page<CalendarHubDto> filterTrm(FilterCalendar filter, Pageable pageable);

     DispatchShiftDto createDispatcher(DispatchShiftDto dto);
     void deleteCalenderHub(Long id );

    DispatchShiftDto findShiftById(Long id);

    DispatchShiftDto findNextShiftById(Long id);

    List<DispatchShiftDto> findByDispatcherShiftByDate(DateDto calendar, Long hubId);

    List<SelectResponse> findShiftByType(Long type, DateDto dateDto, Long hubId);

    List<DispatchShiftDto> getFindByCalendarDisByDateByHub(FindByDispatcherByHubDto dto);
}
