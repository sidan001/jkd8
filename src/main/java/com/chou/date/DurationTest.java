package com.chou.date;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Created by Administrator on 2017/4/18.
 */
public class DurationTest {
    public static void main(String[] args) {
        final LocalTime time1 = LocalTime.of(9, 30, 0);
        final LocalTime time2 = LocalTime.now();

        final LocalDateTime dateTime1 = LocalDateTime.of(2010, 7, 1, 0, 0, 0);
        final LocalDateTime dateTime2 = LocalDateTime.now();

        final Instant instant1 = Instant.now();
        final Instant instant2 = Instant.parse("2010-06-30T16:00:00.00Z");

        Duration d1 = Duration.between(time1, time2);
        Duration d2 = Duration.between(dateTime1, dateTime2);
        Duration d3 = Duration.between(instant2, instant1);

        System.out.println(d3);
        System.out.println(d3.toDays());
        System.out.println(d3.toHours());
        System.out.println(d3.toMinutes());
        System.out.println(d3.toNanos());
        System.out.println(d3.toMillis());

        System.out.println(d3.getSeconds());
        System.out.println(d3.getNano());
        System.out.println(d3.getUnits());

        final long toDays = Duration.between(
                LocalDate.of(2010, 7, 1)
                        .atTime(0,0),
                LocalDate.now()
                        .atTime(0,0))
                .toDays();
        System.out.println("2017-07-01到now相差总天数："+toDays);

        long daysBetween = ChronoUnit.DAYS.between(LocalDate.of(2010, 7, 1),
                LocalDate.now());
        System.out.println("2017-07-01到now相差总天数："+daysBetween);


    }


}
