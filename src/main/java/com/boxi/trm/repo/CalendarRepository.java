package com.boxi.trm.repo;

import com.boxi.trm.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long>, JpaSpecificationExecutor<Calendar> {

    List<Calendar> findCalendarByShamsiDayNumberAndYearNOAndMonthNO(Long Dayno, Long Year, Long Month);

    List<Calendar> findCalendarByShamsiDayNOAndYearNOAndMonthNO(Long Dayno, Long Year, Long Month);



    Calendar findCalendarByShamsi(String Shamsi);

    List<Calendar> findCalendarByYearNOOrderByIdAsc(Long year);


    Calendar findTopByYearNOOrderByIdDesc(Long year);

    Calendar findTopByYearNOOrderByIdAsc(Long year);

    Boolean existsByYearNO(Long year);

    @Query("select distinct c.yearNO from Calendar c order by c.yearNO ")
    List<String> findyears();


}
