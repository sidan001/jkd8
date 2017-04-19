package com.chou.date;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Administrator on 2017/4/19.
 */
public class OffsetDateTimeTest {
    public static void main(String[] args) {
        // Find the last Thursday in July 2013.
        LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneOffset offset = ZoneOffset.of("-08:00");

        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
        OffsetDateTime lastThursday =
                offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));
        System.out.printf("The last Thursday in July 2013 is the %sth.%n",
                lastThursday.getDayOfMonth());
    }
}
