package com.boxi.trm.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.request.DateDto;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.trm.entity.Calendar;
import com.boxi.trm.entity.CalendarHub;
import com.boxi.trm.entity.DispatchShift;
import com.boxi.trm.enums.DAY;
import com.boxi.trm.enums.DispatchShiftType;
import com.boxi.trm.payload.converter.CalendarConverter;
import com.boxi.trm.payload.converter.CalendarHubConverter;
import com.boxi.trm.payload.converter.DispatchShiftConverter;
import com.boxi.trm.payload.dto.*;
import com.boxi.trm.payload.request.FilterCalendar;
import com.boxi.trm.repo.CalendarHubRepository;
import com.boxi.trm.repo.CalendarRepository;
import com.boxi.trm.repo.DispatchShiftRepository;
import com.boxi.trm.service.CalendarService;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarConverter calendarConverter;

    private final CalendarHubRepository calendarHubRepository;
    private final CalendarHubConverter calendarHubConverter;

    private final DispatchShiftConverter dispatchShiftConverter;
    private final DispatchShiftRepository dispatchShiftRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository,
                               CalendarConverter calendarConverter,
                               CalendarHubRepository calendarHubRepository,
                               CalendarHubConverter calendarHubConverter,
                               DispatchShiftConverter dispatchShiftConverter,
                               DispatchShiftRepository dispatchShiftRepository) {
        this.calendarRepository = calendarRepository;
        this.calendarConverter = calendarConverter;
        this.calendarHubRepository = calendarHubRepository;
        this.calendarHubConverter = calendarHubConverter;
        this.dispatchShiftConverter = dispatchShiftConverter;
        this.dispatchShiftRepository = dispatchShiftRepository;
    }

    @Override
    public CalendarHubDto createCalendarHub(CalendarHubDto dto) {
        CalendarHub calendarHub = calendarHubConverter.fromDtoToModel(dto);
        calendarHub.setIsDeleted(false);
        calendarHub.setId(null);
        CalendarHub save = calendarHubRepository.save(calendarHub);


        return calendarHubConverter.fromModelToDto(save);
    }

    @Override
    public CalendarHubDto editCalendarHub(CalendarHubDto dto) {
        CalendarHub calendarHub = calendarHubConverter.fromDtoToModel(dto);
        calendarHub.setIsDeleted(false);

        CalendarHub save = calendarHubRepository.save(calendarHub);


        return calendarHubConverter.fromModelToDto(save);
    }

    @Override
    public List<DispatchShiftDto> create(List<DispatchShiftDto> dto) {
        return saveCreate(dto);
    }

    public Boolean checkDispatcherlist(List<DispatchShift> dispatchShifts) {
        for (DispatchShift dispatchShift : dispatchShifts) {
            if (dispatchShiftRepository.existsByTimeFromBetweenAndHubAndCalendarDate(dispatchShift.getTimeFrom(),
                    dispatchShift.getTimeTo(), dispatchShift.getHub(), dispatchShift.getCalendarDate())) {
                throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.time.exist");
            }
        }

        return true;
    }

    public List<DispatchShiftDto> saveCreate(List<DispatchShiftDto> dto) {

        List<DispatchShift> collect = dto.stream().map(dispatchShiftConverter::fromDtoToModel).collect(Collectors.toList());
        if (checkDispatcherlist(collect)) {
            List<DispatchShiftDto> shiftList = new ArrayList<>();
            for (DispatchShiftDto dispatchShiftDto : dto) {
                DispatchShift dispatchShift = dispatchShiftConverter.fromDtoToModel(dispatchShiftDto);
                dispatchShift.setIsDeleted(false);
                dispatchShift.setIsActive(true);
                DispatchShift save = dispatchShiftRepository.save(dispatchShift);
                shiftList.add(dispatchShiftConverter.fromModelToDto(save));
            }
            return shiftList;
        } else
            throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.time.exist");

    }

    @Override
    public DispatchShiftDto edit(DispatchShiftDto dto) {
        return saveEdit(dto);
    }

    public DispatchShiftDto saveEdit(DispatchShiftDto dto) {

        DispatchShift save = dispatchShiftRepository.save(dispatchShiftConverter.fromDtoToModel(dto));
        return dispatchShiftConverter.fromModelToDto(save);
    }

    @Override
    public void delete(List<SelectResponse> ids) {
        for (SelectResponse id : ids) {
            if (dispatchShiftRepository.existsById(id.getId()))
                dispatchShiftRepository.deleteById(id.getId());
            else
                throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.not.found");
        }
    }

    @Override
    public List<CalendarDto> filter(FilterCalendar filter, Pageable pageable) {


        List<CalendarDto> collect = calendarRepository.findAll((Specification<Calendar>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getYear() != 0)
                predicates.add(criteriaBuilder.equal(root.get("yearNO"), filter.getYear()));

            if (filter.getMonth() != 0)
                predicates.add(criteriaBuilder.equal(root.get("monthNO"), filter.getMonth()));

            if (filter.getDay() != 0)
                predicates.add(criteriaBuilder.equal(root.get("shamsiDayNO"), filter.getDay()));

            if (predicates.size() == 0) {
                String s = DateUtil.nowJalali();
                String[] split = s.split("/");
                predicates.add(criteriaBuilder.equal(root.get("yearNO"), split[0]));
                predicates.add(criteriaBuilder.equal(root.get("monthNO"), split[1]));
            }


            query.orderBy(criteriaBuilder.asc(root.get("id")));
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).stream().map(calendarConverter::fromModelToDto).collect(Collectors.toList());

        for (CalendarDto dto : collect) {

            List<DispatchShift> byCalendarDateAndHub = dispatchShiftRepository.findByCalendarDateAndHubAndCalendarHub(calendarConverter.fromDtoToModel(dto),
                    new Hub().setId(filter.getSelecthub().getId()).setName(filter.getSelecthub().getText()), new CalendarHub().setId(filter.getCalendarhub().getId()));
            dto.setDispatchShifts(byCalendarDateAndHub.stream().map(dispatchShiftConverter::fromModelToDto).collect(Collectors.toList()));


        }
        return collect;
    }

    @Override
    public Page<CalendarHubDto> filterCalendarHub(FilterCalendar filter, Pageable pageable) {

        return calendarHubRepository.findAll((Specification<CalendarHub>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> join = root.join("calendar");
            if (filter.getYear() != 0)
                predicates.add(criteriaBuilder.equal(join.get("yearNO"), filter.getYear()));

            Join<Object, Object> hub = root.join("hub");
            predicates.add(criteriaBuilder.equal(hub.get("id"), filter.getSelecthub().getId()));


            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable).map(calendarHubConverter::fromModelToDto);
    }

    @Override
    public List<DispatchShiftDto> findByDispatcherShift(SelectResponse calendar) {
        List<DispatchShift> shifts = dispatchShiftRepository.findByCalendarDate(calendarRepository.findById(calendar.getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.CALENDAR, "calendar.not.found");
        }));
        return shifts.stream().map(dispatchShiftConverter::fromModelToDto).collect(Collectors.toList());
    }

    public Boolean checkDispatcherlist(List<DispatchShift> dispatchShifts, Calendar calendar) {

        for (DispatchShift dispatchShift : dispatchShifts) {

            List<DispatchShift> from = dispatchShiftRepository.checkDispatcherfrom(dispatchShift.getTimeFrom()
                    , dispatchShift.getHub()
                    , calendar
                    , dispatchShift.getCalendarHub());

            List<DispatchShift> to = dispatchShiftRepository.checkDispatcherto(dispatchShift.getTimeTo()
                    , dispatchShift.getHub()
                    , calendar
                    , dispatchShift.getCalendarHub());
            if (from != null || to != null) {
//                if (dispatchShiftRepository.existsByTimeFromBetweenAndHubAndCalendarDate(dispatchShift.getTimeFrom(), dispatchShift.getTimeTo(), dispatchShift.getHub(),
//                        (calendar != null) ? calendar : dispatchShift.getCalendarDate())) {
                throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.time.exist");
            }
        }

        return true;
    }

    @Override
    public CopyDispatchShiftDto copyDispatchShift(CopyDispatchShiftDto shiftList) {
        List<DispatchShift> collect = shiftList.getShiftDtos().stream().map(dispatchShiftConverter::fromDtoToModel).collect(Collectors.toList());
        if (checkDispatcherlist(collect, new Calendar().setId(shiftList.getNewcalendar().getId()))) {
            findByDispatcherShift(shiftList.getNewcalendar());
            Calendar calendar = calendarRepository.findById(shiftList.getNewcalendar().getId()).orElseThrow();

            for (DispatchShiftDto shiftDto : shiftList.getShiftDtos()) {
                DispatchShift dispatchShift = dispatchShiftConverter.fromDtoToModel(shiftDto);
                dispatchShift.setCalendarDate(calendar);
                dispatchShift.setId(null);
                dispatchShift.setCalendarHub(new CalendarHub().setId(shiftDto.getSelectcalendarhub().getId()));

                dispatchShiftRepository.save(dispatchShift);
            }

            return shiftList;
        } else
            throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.time.exist");
    }

    @Override
    public void deleteSelectShift(List<DispatchShiftDto> shiftList) {
        for (DispatchShiftDto shiftDto : shiftList) {
            dispatchShiftRepository.deleteById(shiftDto.getId());
        }

    }

    public Boolean checkCalendar(List<Calendar> calendars, Hub hub, DispatchShiftType dispatchShiftType) {
        for (Calendar calendar : calendars) {
            List<DispatchShift> byCalendarDateAndHub = dispatchShiftRepository.findByCalendarDateAndHubAndDispatchShiftType(calendar, hub, dispatchShiftType);
            if (!byCalendarDateAndHub.isEmpty())
                return true;
        }
        return false;
    }

    public String normalCreate(List<DispatchShiftDto> dto, Long year, Long month, DAY day, SelectResponse
            selectHub, SelectResponse dispatchShiftType) {
        List<Calendar> calendars = calendarRepository.findCalendarByShamsiDayNOAndYearNOAndMonthNO(day.getValue(), year, month);
        String sReturn = "";
        if (!checkCalendar(calendars, new Hub().setId(selectHub.getId()).setName(selectHub.getText()), DispatchShiftType.findByValue(dispatchShiftType.getId()))) {
            for (Calendar calendar : calendars) {
                int i = 1;
                for (DispatchShiftDto dispatchShiftDto : dto) {
                    dispatchShiftDto.setSelecthub(selectHub);
                    dispatchShiftDto.setCalendarDate(DateUtil.convertDateToJalaliDateDto(calendar.getMiladiDate()));
                    DispatchShift dispatchShift = dispatchShiftConverter.fromDtoToModel(dispatchShiftDto);
                    dispatchShift.setCalendarDate(calendar);
                    dispatchShift.setDispatchShiftType(DispatchShiftType.findByValue(dispatchShiftType.getId()));
                    dispatchShift.setIsDeleted(false);
                    dispatchShift.setIsActive(true);
                    dispatchShift.setPrioraty((long) i);
                    dispatchShift.setCalendarHub(new CalendarHub().setId(dispatchShiftDto.getSelectcalendarhub().getId()));
                    dispatchShiftRepository.save(dispatchShift);
                    i++;
                }

            }
        } else
            sReturn = day.getFa() + " \n ";
        return sReturn;

    }

    @Override
    public String createNormalShift(NormalShiftDto dto) {
        String sReturn = "";

        if (dto.getSaturday() != null)
            sReturn += normalCreate(dto.getSaturday(), dto.getYear(), dto.getMonth(), DAY.SATERDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getSunday() != null)
            sReturn += normalCreate(dto.getSunday(), dto.getYear(), dto.getMonth(), DAY.SUNDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getMonday() != null)
            sReturn += normalCreate(dto.getMonday(), dto.getYear(), dto.getMonth(), DAY.MONDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getTuesday() != null)
            sReturn += normalCreate(dto.getTuesday(), dto.getYear(), dto.getMonth(), DAY.TUESDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getWednesday() != null)
            sReturn += normalCreate(dto.getWednesday(), dto.getYear(), dto.getMonth(), DAY.WEDNESDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getThursday() != null)
            sReturn += normalCreate(dto.getThursday(), dto.getYear(), dto.getMonth(), DAY.THURSDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        if (dto.getFriday() != null)
            sReturn += normalCreate(dto.getFriday(), dto.getYear(), dto.getMonth(), DAY.FRIDAY, dto.getSelecthub(), dto.getDispatchShiftType());

        return sReturn;

    }

    @Override
    public void copyYearHubByWeekday(CopyYearHubDto dto) {

        Calendar newStart = calendarRepository.findTopByYearNOOrderByIdAsc(dto.getNewyear());

        List<Calendar> oldCalendar = calendarRepository.findCalendarByYearNOOrderByIdAsc(dto.getOldyear());
        List<Calendar> newCalendar = calendarRepository.findCalendarByYearNOOrderByIdAsc(dto.getNewyear());

        Long newStartDay = newStart.getShamsiDayNO();


        int gap = 0;
        for (Calendar calendar : oldCalendar) {
            if (Objects.equals(calendar.getShamsiDayNO(), newStartDay)) {
                log.warn("----------->" + gap);
                break;
            }
            gap++;
        }
        List<CalendarDto> newCollectCal = newCalendar.stream().map(calendarConverter::fromModelToDto).collect(Collectors.toList());
        for (CalendarDto calendarDto : newCollectCal) {
            try {
                List<DispatchShift> dispatchShifts = dispatchShiftRepository.findByCalendarDateAndHub(oldCalendar.get(gap), new Hub().setId(dto.getSelecthub().getId()));
                List<DispatchShiftDto> collect1 = dispatchShifts.stream().map(dispatchShiftConverter::fromModelToDto).collect(Collectors.toList());
                log.warn("----------->" + calendarDto.getId() + "  -  " + oldCalendar.get(gap).getId() + "  -> new day : " + calendarDto.getShamsiDayName() + " -> old day " + oldCalendar.get(gap).getShamsiDayName());
                List<DispatchShift> collect = collect1.stream().peek(c -> {
                    c.setId(null);
                    c.setIsActive(true);
                    c.setIsDeleted(false);

                    String[] split = c.getSelectcalendar().getText().split("/");
                    split[0] = String.valueOf(dto.getNewyear());
                    String newPersianDate = split[0] + split[1] + split[2];
                    c.setCalendarDate(new DateDto().setYear(Integer.valueOf(split[0])).setMonth(Integer.valueOf(split[1])).setDay(Integer.valueOf(split[2])));
                    c.setSelectcalendar(new SelectResponse(Long.valueOf(newPersianDate), newPersianDate));

                }).map(dispatchShiftConverter::fromDtoToModel).collect(Collectors.toList());
                dispatchShiftRepository.saveAll(collect);
            } catch (Exception e) {
                break;
            }
            gap++;
        }


    }


    @Override
    public void copyYearHubByDay(CopyYearHubDto dto) {

        CalendarHub calendarHub = calendarHubRepository.findById(dto.getSelectCalenderhub().getId()).orElseThrow();
        CalendarHubDto calendarHubDto = calendarHubConverter.fromModelToDto(calendarHub);

        calendarHubDto.setName(calendarHub.getName() + "_" + dto.getNewyear());
        calendarHubDto.setId(null);
        CalendarHubDto hub = createCalendarHub(calendarHubDto);

        for (DispatchShift shift : dispatchShiftRepository.findAllByCalendarHub(calendarHub)) {

            String newYear = String.valueOf(shift.getCalendarDate().getId()).replace(dto.getOldyear().toString(), dto.getNewyear().toString());
            shift.setCalendarDate(new Calendar().setId(Long.valueOf(newYear)));
            shift.setCalendarHub(new CalendarHub().setId(hub.getId()));
            shift.setHub(new Hub().setId(dto.getSelecthub().getId()));

            DispatchShiftDto dispatchShiftDto = dispatchShiftConverter.fromModelToDto(shift);
            dispatchShiftDto.setIsActive(true);
            createDispatcher(dispatchShiftDto);


        }


    }

    @Override
    public List<SelectResponse> getYears() {
        return calendarRepository.findyears().stream().map(this::selectTo).collect(Collectors.toList());
    }

    private List<Long> findChild(List<HubPermissionDto> hubList, Long parentId) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            if (Objects.equals(hubPermissionDto.getParent(), parentId)) {
                list.add(hubPermissionDto.getId());
                if (hubPermissionDto.getChildren() != null)
                    list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
            } else
                list.add(hubPermissionDto.getId());
        }
        return list;
    }

    private List<Long> findAllHubId(List<HubPermissionDto> hubList) {
        List<Long> list = new ArrayList<>();
        for (HubPermissionDto hubPermissionDto : hubList) {
            list.add(hubPermissionDto.getId());
            if (hubPermissionDto.getChildren() != null)
                list.addAll(findChild(hubPermissionDto.getChildren(), hubPermissionDto.getId()));
        }
        return list;
    }

    @Override
    public Page<CalendarHubDto> filterTrm(FilterCalendar filter, Pageable pageable) {


        Page<CalendarHub> all = calendarHubRepository.findAll((Specification<CalendarHub>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("yearNO"), filter.getYear()));

            if (filter.getHublist() != null) {
                List<Long> ids = findAllHubId(filter.getHublist());
                predicates.add(criteriaBuilder.and(root.get("hub").get("id").in(ids)));
            } else
                predicates.add(criteriaBuilder.equal(root.get("hub").get("id"), filter.getSelecthub().getId()));

            if (StringUtils.isNotBlank(filter.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));


            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);


        return all.map(calendarHubConverter::fromModelToDto);
    }

    @Override
    public DispatchShiftDto createDispatcher(DispatchShiftDto dto) {

        DispatchShift shift = dispatchShiftConverter.fromDtoToModel(dto);
        String[] splitFrom = dto.getTimeFrom().split(":");
        Timestamp from = DateUtil.convertJalaliDayTimeToTimeStampWithTime(dto.getCalendarDate(), Integer.parseInt(splitFrom[0]), Integer.parseInt(splitFrom[1]));

        String[] splitTo = dto.getTimeTo().split(":");
        Timestamp to = DateUtil.convertJalaliDayTimeToTimeStampWithTime(dto.getCalendarDate(), Integer.parseInt(splitTo[0]), Integer.parseInt(splitTo[1]));

        List<DispatchShift> strings = dispatchShiftRepository.checkDispatcherfrom(from
                , new Hub().setId(dto.getSelecthub().getId())
                , new Calendar().setId(dto.getSelectcalendar().getId())
                , new CalendarHub().setId(dto.getSelectcalendarhub().getId()));

        List<DispatchShift> strings2 = dispatchShiftRepository.checkDispatcherto(to
                , new Hub().setId(dto.getSelecthub().getId())
                , new Calendar().setId(dto.getSelectcalendar().getId())
                , new CalendarHub().setId(dto.getSelectcalendarhub().getId()));
        if (strings.size() == 0 && strings2.size() == 0) {
            shift.setCalendarHub(new CalendarHub().setId(dto.getSelectcalendarhub().getId()));
            shift.setCalendarDate(new Calendar().setId(dto.getSelectcalendar().getId()));
            shift.setHub(new Hub().setId(dto.getSelecthub().getId()));

            shift.setId(null);
            shift.setIsDeleted(false);
            DispatchShift save = dispatchShiftRepository.save(shift);

            return dispatchShiftConverter.fromModelToDto(save);

        } else
            throw BusinessException.valueException(EntityType.CALENDAR, "dispatcher.time.exist");


    }

    @Override
    public void deleteCalenderHub(Long id) {

        dispatchShiftRepository.deleteAllByCalendarHub(calendarHubRepository.findById(id).orElseThrow());
        calendarHubRepository.deleteById(id);
//        TODO Delete calendar hub
    }

    @Override
    public DispatchShiftDto findShiftById(Long id) {
        DispatchShift shift = dispatchShiftRepository.findById(id).orElseThrow();
        return dispatchShiftConverter.fromModelToDto(shift);
    }

    @Override
    public DispatchShiftDto findNextShiftById(Long id) {
        DispatchShift shift = dispatchShiftRepository.findById(id).orElseThrow();
        DispatchShift firstByIdAfterAndHubAndCalendarDate = dispatchShiftRepository.findFirstByIdAfterAndHubAndCalendarDate(shift.getId(),
                shift.getHub(), shift.getCalendarDate());

        return dispatchShiftConverter.fromModelToDto(firstByIdAfterAndHubAndCalendarDate);

    }

    @Override
    public List<DispatchShiftDto> findByDispatcherShiftByDate(DateDto calendar, Long hubId) {

        List<Calendar> calendars = calendarRepository.findCalendarByShamsiDayNumberAndYearNOAndMonthNO(Long.valueOf(calendar.getDay())
                , Long.valueOf(calendar.getYear()), Long.valueOf(calendar.getMonth()));

        return findByDispatcherShift(new SelectResponse(calendars.get(0).getId(), calendars.get(0).getShamsi()));
    }

    @Override
    public List<SelectResponse> findShiftByType(Long type, DateDto dateDto, Long hubId) {
        List<DispatchShift> all = dispatchShiftRepository.findAll((Specification<DispatchShift>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("dispatchShiftType"), DispatchShiftType.findByValue(type)));


            String MyDate = dateDto.getYear().toString();
            String month = "0" + dateDto.getMonth().toString();
            month = month.substring(month.length() - 2, month.length());
            String days = "0" + dateDto.getDay().toString();
            days = days.substring(days.length() - 2, days.length());
            MyDate += month + days;

            predicates.add(criteriaBuilder.equal(root.get("calendarDate").get("id"), MyDate));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));

            predicates.add(criteriaBuilder.equal(root.get("hub").get("id"), hubId));

            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });

        return all.stream().map(this::toSelect).collect(Collectors.toList());
    }

    @Override
    public List<DispatchShiftDto> getFindByCalendarDisByDateByHub(FindByDispatcherByHubDto dto) {
        String MyDate = dto.getDateDto().getYear().toString();
        String month = "0" + dto.getDateDto().getMonth().toString();
        month = month.substring(month.length() - 2, month.length());
        String days = "0" + dto.getDateDto().getDay().toString();
        days = days.substring(days.length() - 2, days.length());
        MyDate += month + days;
        List<DispatchShift> byCalendarDateAndHub;
        if (dto.getShiftType() == null) {
            byCalendarDateAndHub = dispatchShiftRepository.findByCalendarDateAndHub(new Calendar().setId(Long.valueOf(MyDate)), new Hub().setId(dto.getHubid()));
        } else {

            Long finalMyDate = Long.valueOf(MyDate);
            byCalendarDateAndHub=  dispatchShiftRepository.findAll((Specification<DispatchShift>) (root, query, criteriaBuilder)->{
               List<Predicate> predicates = new ArrayList<>();
               predicates.add(criteriaBuilder.equal(root.get("hub").get("id"),dto.getHubid()));
               predicates.add(criteriaBuilder.equal(root.get("calendarDate").get("id"), finalMyDate));
                Predicate dispatchShiftType = criteriaBuilder.equal(root.get("dispatchShiftType"), DispatchShiftType.findByValue(dto.getShiftType().getId()));
                Predicate dispatchShiftType2 = criteriaBuilder.equal(root.get("dispatchShiftType"), DispatchShiftType.PICKUPDELIVERY);
                predicates.add(criteriaBuilder.or(dispatchShiftType,dispatchShiftType2));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            });

        }


        return byCalendarDateAndHub.stream().map(dispatchShiftConverter::fromModelToDto).collect(Collectors.toList());

    }

    private SelectResponse toSelect(DispatchShift dispatchShift) {
        return new SelectResponse(dispatchShift.getId(), dispatchShift.getTimeFrom() + " - " + dispatchShift.getTimeTo());
    }

    private SelectResponse selectTo(String s) {
        return new SelectResponse(Long.valueOf(s), s);
    }


}
