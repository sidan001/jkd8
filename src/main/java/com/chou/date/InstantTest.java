package com.chou.date;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created by Administrator on 2017/4/18.
 */
public class InstantTest {
    public static void main(String[] args) {
        System.out.println(Instant.now());//2017-04-18T07:24:09.932Z   Z标示没有时区
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));//2017-04-18T15:40:49.721+08:00[Asia/Shanghai]

        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));//2秒之后再加上100百万纳秒（1秒）
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));//4秒之前的100百万纳秒（1秒）

        //how many seconds have occurred since the beginning of the Java epoch.
        long secondsFromEpoch = Instant.ofEpochSecond(0L)
                .until(Instant.now(), ChronoUnit.SECONDS);

        System.out.printf("%d seconds have occurred since the beginning of the Java epoch.%n",
                secondsFromEpoch);


        //The Instant class does not work with human units of time, such as years, months, or days.
        // If you want to perform calculations in those units, you can convert an Instant to another class,
        // such as LocalDateTime or ZonedDateTime, by binding the Instant with a time zone.
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());



        final ZonedDateTime zonedDateTime = ZonedDateTime.now();
        final Instant ZDT2Instant = zonedDateTime.toInstant();
        System.out.printf("%40s%s%n", "ZonedDateTime now : ",zonedDateTime);
        System.out.printf("%40s%s%n", "ZonedDateTime now convert to Instant : ",ZDT2Instant);

        final ZonedDateTime instant2ZDT = ZonedDateTime.ofInstant(ZDT2Instant, ZoneId.systemDefault());
        System.out.printf("%40s%s%n","Instant now convert to  ZonedDateTime : ", instant2ZDT);
        System.out.println(instant2ZDT.equals(zonedDateTime));

    }
}
