package com.boxi.utils;


import com.boxi.core.request.DateDto;
import com.boxi.utils.jalali.DateConverter;
import com.boxi.utils.jalali.JalaliDate;
import com.boxi.utils.jalali.JalaliDateFormatter;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class DateUtil {
//    public static void main(String[] args) {
//        System.out.println(convertHHmmToMinute("11:00"));
//        System.out.println(convertHHmmToMinute("01:05"));
//        System.out.println(convertHHmmToMinute("60:10"));
//        System.out.println("***************************");
//        System.out.println(convertMinuteToHHmm(660));
//        System.out.println(convertMinuteToHHmm(65));
//        System.out.println(convertMinuteToHHmm(3610));
//    }

    static ZoneId tehranZone = ZoneId.of("Asia/Tehran");

    public static synchronized Timestamp tomorrow() {
        LocalDate date = LocalDate.now();
        date = date.plusDays(1);
        return Timestamp.valueOf(date.atStartOfDay());
    }

    public static synchronized String convertMiladiToJalali(Date date) {
        String s = date.toString();
        DateConverter dateConverter = new DateConverter();
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]), Integer.parseInt(s.split("-")[2]));
        return jalaliDate.toString();

    }

    public static synchronized String convertEpochToJalali(long epoch) {
        DateConverter dateConverter = new DateConverter();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), tehranZone);
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        return jalaliDate.format(new JalaliDateFormatter("yyyy/mm/dd", JalaliDateFormatter.FORMAT_IN_PERSIAN));
    }

    public static synchronized DateDto convertEpochToJalaliDate(long epoch) {
        DateConverter dateConverter = new DateConverter();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), tehranZone);
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        return new DateDto().setYear(jalaliDate.getYear()).setMonth(jalaliDate.getMonthPersian().getValue()).setDay(jalaliDate.getDay());
    }


    public static synchronized DateDto convertDateToJalaliDateDto(Date date) {
        //    2022/01/11
        long epoch = date.getTime() / 1000;
        return convertEpochToJalaliDate(epoch);


    }


    public static synchronized Date convertDateToJalaliDateDto(DateDto dto) {
        DateConverter dateConverter = new DateConverter();
        LocalDate gregorian = dateConverter.jalaliToGregorian(dto.getYear(), dto.getMonth(), dto.getDay());
        return Date.from(gregorian.atStartOfDay(tehranZone).toInstant());
    }

    public static synchronized DateDto convertEpochToJalaliObject(long epoch) {
        DateConverter dateConverter = new DateConverter();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), tehranZone);
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth());
        return new DateDto().setYear(jalaliDate.getYear()).setMonth(jalaliDate.getMonthPersian().getValue()).setDay(jalaliDate.getDay());

    }

    public static synchronized String nowJalali() {
        long epoch = Instant.now().toEpochMilli();
        return convertEpochToJalali(epoch / 1000);
    }

    public static Timestamp nowTimeStamp() {
        return new Timestamp(Instant.now().toEpochMilli() / 1000);
    }

    public static synchronized String uniqueJalaliNumber(String prefix) {
        DateConverter dateConverter = new DateConverter();
        LocalDateTime currentdate = LocalDateTime.now();
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(currentdate.getYear(), currentdate.getMonth(), currentdate.getDayOfMonth());
        String ob = currentdate.getHour() + "" + currentdate.getMinute() + "" + currentdate.getSecond() + "" + (prefix != null ? prefix : currentdate.getNano() / 1000);
        return jalaliDate.format(new JalaliDateFormatter("yyyymmdd", JalaliDateFormatter.FORMAT_IN_ENGLISH)) + "" + ob;
    }


    public synchronized static Timestamp convertJalaliDayTimeToTimeStamp(Integer day, Integer month, Integer year) {
        //include format sample 144/01/05 11:30
        DateConverter dateConverter = new DateConverter();
        LocalDate gregorian = dateConverter.jalaliToGregorian(year, month, day);
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, gregorian.getYear());
        cal.set(Calendar.MONTH, gregorian.getMonth().getValue() - 1);
        cal.set(Calendar.DATE, gregorian.getDayOfMonth());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // now convert GregorianCalendar object to Timestamp object
        return new Timestamp(cal.getTimeInMillis());
    }

    public synchronized static Timestamp convertJalaliDayTimeToTimeStamp(DateDto date) {
        //include format sample 144/01/05 11:30
        if (date != null) {
            DateConverter dateConverter = new DateConverter();
            LocalDate gregorian = dateConverter.jalaliToGregorian(date.getYear(), date.getMonth(), date.getDay());
            Calendar cal = new GregorianCalendar();
            cal.set(Calendar.YEAR, gregorian.getYear());
            cal.set(Calendar.MONTH, gregorian.getMonth().getValue() - 1);
            cal.set(Calendar.DATE, gregorian.getDayOfMonth());
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR, 0);
            // now convert GregorianCalendar object to Timestamp object
            return new Timestamp(cal.getTimeInMillis());
        } else {
            return null;
        }
    }

    public synchronized static Timestamp convertJalaliDayTimeToTimeStampWithTime(DateDto date, Integer hour, Integer minute) {
        //include format sample 144/01/05 11:30
        if (date != null) {
            DateConverter dateConverter = new DateConverter();
            LocalDate gregorian = dateConverter.jalaliToGregorian(date.getYear(), date.getMonth(), date.getDay());
            Calendar cal = new GregorianCalendar();


            cal.set(Calendar.YEAR, gregorian.getYear());
            cal.set(Calendar.MONTH, gregorian.getMonth().getValue() - 1);
            cal.set(Calendar.DATE, gregorian.getDayOfMonth());
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            // now convert GregorianCalendar object to Timestamp object
            return new Timestamp(cal.getTimeInMillis());

        } else {
            return null;
        }
    }

    public synchronized static String convertTimeStampStringToJalali(String timestamp) {
        //such String timestamp = "2021-05-13 11:59:00.000"; time stamp format
        ZonedDateTime zonedDateTime = LocalDateTime.parse(
                timestamp,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        ).atZone(tehranZone);
        long epoch = zonedDateTime.toInstant().toEpochMilli();
        return convertEpochToJalali(epoch);
    }

    public synchronized static DateDto convertTimeStampStringToJalaliObject(Timestamp timestamp) {
        long epoch = timestamp.toInstant().toEpochMilli();
        return convertEpochToJalaliObject(epoch / 1000);
    }

    public synchronized static String convertTimeStampToJalali(Timestamp timestamp) {
        //such String timestamp = "2021-05-13 11:59:00.000"; time stamp format
        long epoch = timestamp.toInstant().toEpochMilli();
        return convertEpochToJalali(epoch);
    }

    public synchronized static Long convertHHmmToMinute(String HHmm) {
        //samples 11:00 01:05 60:10
        String[] parts = HHmm.split(":");
        long HH = Long.parseLong((parts[0])); // 56
        long mm = Long.parseLong(parts[1]); // 01
        System.out.println(HH + ":" + mm);
        return TimeUnit.HOURS.toMinutes(HH) + mm;
    }

    public synchronized static String convertMinuteToHHmm(int mm) {
        //samples 660 65 3610
        int hour = mm / 60; //since both are ints, you get an int
        int minute = mm % 60;
        return String.format("%02d:%02d", hour, minute);
    }

    public static Boolean isBetweenDates(Date now, Date from, Date to) {
        return now.after(from) && now.before(to);
    }
}
