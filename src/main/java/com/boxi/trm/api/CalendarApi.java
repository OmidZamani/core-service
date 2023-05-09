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
    public Response createcalender(@RequestBody CalendarHubDto dto) {

        return Response.ok().setPayload(calendarService.createcalendarhub(dto));
    }

    @PutMapping
    public Response editcalender(@RequestBody DispatchShiftDto dto) {
        log.warn(dto.toJson());
        return Response.ok().setPayload(calendarService.edit(dto));
    }

    @PutMapping("/calendar")
    public Response editcalenderhub(@RequestBody CalendarHubDto dto) {

        return Response.ok().setPayload(calendarService.editcalendarhub(dto));
    }

    @DeleteMapping("/")
    public Response delete(@RequestBody List<SelectResponse> ids) {
        calendarService.delete(ids);
        return Response.ok();
    }

    @GetMapping("/month")
    public Response getmonth() {
        return Response.ok().setPayload(MONTH.select());
    }

    @GetMapping("/day")
    public Response getday() {
        return Response.ok().setPayload(DAY.select());
    }

    @GetMapping("/dispatchshift")
    public Response getdispatchshift() {
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
    public Response getfindbycalendardis(@RequestBody SelectResponse calendar) {
        return Response.ok().setPayload(calendarService.findbyDispatchershift(calendar));
    }

    @PostMapping("/dispatchshiftbydate")
    public Response getfindbycalendardisByDate(@RequestBody DateDto calendar) {

        return Response.ok().setPayload(calendarService.findbyDispatchershiftByDate(calendar,null));
    }

    @PostMapping("/dispatchshiftbydateandHub")
    public Response getfindbycalendardisByDateByhub(@RequestBody FindByDispatcherByHubDto dto) {

        return Response.ok().setPayload(calendarService.getfindbycalendardisByDateByhub(dto));
    }
    @PostMapping("/copyshift")
    public Response copyshift(@RequestBody CopyDispatchShiftDto calendar) {
        return Response.ok().setPayload(calendarService.copyDispatchShift(calendar));
    }


    @PostMapping("/createnormalshift")
    public Response deleteselectshift(@RequestBody NormalShiftDto shift) {
        String createnormalshift = calendarService.createnormalshift(shift);
        if (!createnormalshift.equals(""))
            throw BusinessException.valueException(EntityType.CALENDAR, "calender.dispatcher.is.exists", createnormalshift);
        return Response.ok();
    }

    @PostMapping("/copyyearhubbyday")
    public Response copyyearhubbyday(@RequestBody CopyYearHubDto dto) {
        calendarService.copyyearhubbyday(dto);
        return Response.ok();
    }

    @PostMapping("/copyyearhubbyweekday")
    public Response copyyearhubbyweekday(@RequestBody CopyYearHubDto dto) {
        calendarService.copyyearhubbyweekday(dto);
        return Response.ok();
    }

    @GetMapping("/year")
    public Response getyear() {

        return Response.ok().setPayload(calendarService.getyears());
    }

    @PostMapping("/filtertrm")
    public Response filtertrm(@RequestParam(name = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                              @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                              @RequestBody FilterCalendar request) {

        log.warn(request.toJson());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return Response.ok().setPayload(calendarService.filtertrm(request, pageable));

    }

    @PostMapping("/dispatcher")
    public Response createdispatcher(@RequestBody DispatchShiftDto dto) {
        return Response.ok().setPayload(calendarService.createDispatcher(dto));
    }

    @DeleteMapping("/calendar/{id}")
    public Response deletecalendat(@PathVariable Long id) {
        calendarService.deletecalenderhub(id);
        return Response.ok();
    }

    @GetMapping("/findshiftbyid/{id}")
    public DispatchShiftDto findshiftbyid(@PathVariable Long id) {
        DispatchShiftDto findshiftbyid = calendarService.findshiftbyid(id);
        return findshiftbyid;
    }
    @GetMapping("/findnextshiftbyid/{id}")
    public DispatchShiftDto findnextshiftbyid(@PathVariable Long id) {
        DispatchShiftDto findshiftbyid = calendarService.findnextshiftbyid(id);
        return findshiftbyid;
    }

    @PostMapping("/findShiftBytype/{type}/{hubid}")
    public List<SelectResponse> findShiftBytype(@PathVariable Long type ,@RequestBody DateDto dateDto,@PathVariable Long hubid ) {
        List<SelectResponse> findshiftbyid = calendarService.findShiftBytype(type,dateDto,hubid);
        return findshiftbyid;
    }




}
