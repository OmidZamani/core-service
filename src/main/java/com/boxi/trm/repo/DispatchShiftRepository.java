package com.boxi.trm.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.trm.entity.Calendar;
import com.boxi.trm.entity.CalendarHub;
import com.boxi.trm.entity.DispatchShift;
import com.boxi.trm.enums.DispatchShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DispatchShiftRepository extends JpaRepository<DispatchShift, Long>, JpaSpecificationExecutor<DispatchShift> {
    List<DispatchShift> findByCalendarDate(Calendar calendar);

    List<DispatchShift> findByCalendarDateAndHub(Calendar calendar, Hub hub);


    List<DispatchShift> findByCalendarDateAndHubAndDispatchShiftType(Calendar calendar, Hub hub, DispatchShiftType dispatchShiftType);

    List<DispatchShift> findByCalendarDateAndHubAndDispatchShiftTypeOrDispatchShiftType(Calendar calendar, Hub hub, DispatchShiftType dispatchShiftType, DispatchShiftType dispatchShiftTypeall);

    List<DispatchShift> findByCalendarDateAndHubAndCalendarHub(Calendar calendar, Hub hub, CalendarHub calendarHub);

    Boolean existsByTimeFromBetweenAndHubAndCalendarDate(Timestamp startTime, Timestamp endTime, Hub hub, Calendar calendar);

    @Query(value = "select d from DispatchShift d where ?1 between d.timeFrom  and d.timeTo and d.hub =?2  and d.calendarDate=?3 and d.calendarHub =?4 and d.dispatchShiftType =?5")
    List<DispatchShift> checkDispatcherfrom(Timestamp startTime, Hub hub, Calendar calendar, CalendarHub calendarHub,DispatchShiftType dispatchShiftType);

    @Query(value = "select d from DispatchShift d where ?1 between d.timeFrom  and d.timeTo  and d.hub =?2  and d.calendarDate=?3 and d.calendarHub =?4 and d.dispatchShiftType =?5")
    List<DispatchShift> checkDispatcherto(Timestamp endTime, Hub hub, Calendar calendar, CalendarHub calendarHub,DispatchShiftType dispatchShiftType);


    @Query(value = "SELECT  d from DispatchShift d where d.id>?2 and d.calendarDate =?3 and d.hub =?1     order by d.timeFrom asc  ")
    DispatchShift findNextShift(Hub hub, Long lateShiftId, java.util.Calendar calendar, Pageable pageable);

    DispatchShift findFirstByIdAfterAndHubAndCalendarDate(Long Id, Hub hub, Calendar calendar);


    @Modifying
    void deleteAllByCalendarHub(CalendarHub calendarHub);

    List<DispatchShift> findAllByCalendarHub(CalendarHub calendarHub);

}
