package com.boxi.trm.api;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.Response;
import com.boxi.core.response.SelectResponse;
import com.boxi.trm.enums.DAY;
import com.boxi.trm.enums.DispatchShiftType;
import com.boxi.trm.enums.MONTH;
import com.boxi.trm.payload.dto.*;
import com.boxi.trm.payload.request.FilterCalendar;
import com.boxi.trm.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core-api/calendar")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CalendarApi {
    private final CalendarService calendarService;

    @PostMapping
    public Response createCalender(@RequestBody CalendarHubDto dto) {

        return Response.ok().setPayload(calendarService.createCalendarHub(dto));
    }

    @PutMapping
    public Response editCalender(@RequestBody DispatchShiftDto dto) {
        log.warn(dto.toJson());
        return Response.ok().setPayload(calendarService.edit(dto));
    }

    @PutMapping("/calendar")
    public Response editCalenderHub(@RequestBody CalendarHubDto dto) {

        return Response.ok().setPayload(calendarService.editCalendarHub(dto));
    }

    @DeleteMapping("/")
    public Response delete(@RequestBody List<SelectResponse> ids) {
        calendarService.delete(ids);
        return Response.ok();
    }

    @GetMapping("/month")
    public Response getMonth() {
        return Response.ok().setPayload(MONTH.select());
    }

    @GetMapping("/day")
    public Response getDay() {
        return Response.ok().setPayload(DAY.select());
    }

    @GetMapping("/dispatchshift")
    public Response getDispatchShift() {
        return Response.ok().setPayload(DispatchShiftType.select());
    }

    @PostMapping("/filter")
    public Response filter(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                           @RequestBody FilterCalendar request) {
        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(calendarService.filter(request, pageable));
    }

    @PostMapping("/dispatchshift")
    public Response getFindByCalendarDis(@RequestBody SelectResponse calendar) {
        return Response.ok().setPayload(calendarService.findByDispatcherShift(calendar));
    }

    @PostMapping("/dispatchshiftbydate")
    public Response getFindByCalendarDisByDate(@RequestBody DateDto calendar) {

        return Response.ok().setPayload(calendarService.findByDispatcherShiftByDate(calendar,null));
    }

    @PostMapping("/dispatchshiftbydateandHub")
    public Response getFindByCalendarDisByDateByHub(@RequestBody FindByDispatcherByHubDto dto) {

        return Response.ok().setPayload(calendarService.getFindByCalendarDisByDateByHub(dto));
    }
    @PostMapping("/copyshift")
    public Response copyShift(@RequestBody CopyDispatchShiftDto calendar) {
        return Response.ok().setPayload(calendarService.copyDispatchShift(calendar));
    }


    @PostMapping("/createnormalshift")
    public Response deleteSelectShift(@RequestBody NormalShiftDto shift) {
        String s = calendarService.createNormalShift(shift);
        if (!s.equals(""))
            throw BusinessException.valueException(EntityType.CALENDAR, "calender.dispatcher.is.exists", s);
        return Response.ok();
    }

    @PostMapping("/copyyearhubbyday")
    public Response copyYearHubByDay(@RequestBody CopyYearHubDto dto) {
        calendarService.copyYearHubByDay(dto);
        return Response.ok();
    }

    @PostMapping("/copyyearhubbyweekday")
    public Response copyYearHubByWeekDay(@RequestBody CopyYearHubDto dto) {
        calendarService.copyYearHubByWeekday(dto);
        return Response.ok();
    }

    @GetMapping("/year")
    public Response getYear() {
        return Response.ok().setPayload(calendarService.getYears());
    }

    @PostMapping("/filtertrm")
    public Response filterTrm(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                              @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                              @RequestBody FilterCalendar request) {

        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(calendarService.filterTrm(request, pageable));

    }

    @PostMapping("/dispatcher")
    public Response createDispatcher(@RequestBody DispatchShiftDto dto) {
        return Response.ok().setPayload(calendarService.createDispatcher(dto));
    }

    @DeleteMapping("/calendar/{id}")
    public Response deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalenderHub(id);
        return Response.ok();
    }

    @GetMapping("/findshiftbyid/{id}")
    public DispatchShiftDto findshiftbyid(@PathVariable Long id) {

        return calendarService.findShiftById(id);
    }
    @GetMapping("/findnextshiftbyid/{id}")
    public DispatchShiftDto findnextshiftbyid(@PathVariable Long id) {

        return calendarService.findNextShiftById(id);
    }

    @PostMapping("/findShiftBytype/{type}/{hubid}")
    public List<SelectResponse> findShiftByType(@PathVariable Long type ,@RequestBody DateDto dateDto,@PathVariable Long hubid ) {

        return calendarService.findShiftByType(type,dateDto,hubid);
    }




}
