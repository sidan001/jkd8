package com.chou.date;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class ZoneIdAndZoneOffset {
    public static void main(String[] args) {

        printOffsetNotInWholeHours();

        System.out.println(ZonedDateTime.now());//2017-04-19T15:05:16.750+08:00[Asia/Shanghai]
        System.out.println(OffsetDateTime.now());//2017-04-19T15:05:16.751+08:00
        System.out.println(OffsetTime.now());//15:05:16.751+08:00

    }

    /**
     * Offsets from Greenwich/UTC time are usually defined in whole hours, but there are exceptions.
     * The following code, from the TimeZoneId example, prints a list of all time zones that
     * use offsets from Greenwich/UTC that are not defined in whole hours.
     *
     *  Offsets一般定义为整数小时，但也有例外，下面程序打印出不是整数小时的时区
     */
    private static void printOffsetNotInWholeHours() {
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();

        // Create a List using the set of zones and sort it.
        List<String> zoneList = new ArrayList<String>(allZones);
        Collections.sort(zoneList);


        for (String s : zoneList) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
            String out = String.format("%35s %10s%n", zone, offset);

            // Write only time zones that do not have a whole hour offset
            // to standard out.
            if (secondsOfHour != 0) {
                System.out.printf(out);
            }
        }
    }
}
