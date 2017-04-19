package com.chou.date;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/4/19.
 */
public class DayOfWeekTest {
    public static void main(String[] args) {
        System.out.printf("%s%n", DayOfWeek.MONDAY.plus(3));

        DayOfWeek dow = DayOfWeek.MONDAY;
        Locale locale = Locale.getDefault();
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));




        Arrays.stream(DayOfWeek.values())
                .forEach(dw ->
                        System.out.println(dw.getDisplayName(TextStyle.FULL,  Locale.ENGLISH)));

        Stream.of(DayOfWeek.values())
                .forEach(dw ->
                        System.out.println(dw.getDisplayName(TextStyle.FULL,  Locale.JAPAN)));

        Stream.of(DayOfWeek.values())
                .forEach(dw ->
                        System.out.println(dw.getDisplayName(TextStyle.FULL,  Locale.CHINA)));

    }
}
