package com.chou.date;

import java.time.*;

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


        System.out.println(LocalDateTime.now());//2017-04-19T11:12:48.786  已经包含时区，+8：00
        System.out.println(Instant.now());//2017-04-19T03:12:48.787Z        不包含时区


        System.out.printf("now: %s%n", LocalDateTime.now());

        System.out.printf("Apr 15, 1994 @ 11:30am: %s%n",
                LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));

        System.out.printf("now (from Instant): %s%n",
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

        System.out.printf("6 months from now: %s%n",
                LocalDateTime.now().plusMonths(6));

        System.out.printf("6 months ago: %s%n",
                LocalDateTime.now().minusMonths(6));
    }
}
