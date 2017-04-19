package com.chou.date;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/4/19.
 */
public class MonthTest {
    public static void main(String[] args) {
        System.out.printf("%d%n", Month.FEBRUARY.maxLength());

        Month month = Month.AUGUST;
        Locale locale = Locale.getDefault();
        System.out.println(month.getDisplayName(TextStyle.FULL, locale));
        System.out.println(month.getDisplayName(TextStyle.NARROW, locale));
        System.out.println(month.getDisplayName(TextStyle.SHORT, locale));


        Stream.of(Month.values())
                .forEach(m ->
                        System.out.println(m.getDisplayName(TextStyle.FULL,  Locale.ENGLISH)));

        Stream.of(Month.values())
                .forEach(m ->
                        System.out.println(m.getDisplayName(TextStyle.FULL,  Locale.JAPAN)));

        Stream.of(Month.values())
                .forEach(m ->
                        System.out.println(m.getDisplayName(TextStyle.FULL,  Locale.CHINA)));
    }
}
