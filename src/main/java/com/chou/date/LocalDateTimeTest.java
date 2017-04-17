package com.chou.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Created by Administrator on 2017/4/17.
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        //UTC – The World's Time Standard , Not a Time Zone
        //Coordinated Universal Time (UTC) is the basis for civil time today.
        // 2017-04-17T13:45:20

        LocalDate date = LocalDate.parse("2017-04-17");
        LocalTime time = LocalTime.parse("13:45:20");

        LocalDateTime dt1 = LocalDateTime.of(2017, Month.APRIL, 17, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        final LocalDate localDate = dt1.toLocalDate();
        final LocalTime localTime = dt1.toLocalTime();


    }
}
