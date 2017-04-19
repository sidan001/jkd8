package com.chou.date;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/4/19.
 */
public class MonthDayTest {
    public static void main(String[] args) {
        MonthDay date = MonthDay.of(Month.FEBRUARY, 29);
        boolean validLeapYear = date.isValidYear(2010);
        System.out.println(validLeapYear);

        //找出1988-2100范围内的闰年
        IntStream.rangeClosed(1988,2100)
                .filter(i -> date.isValidYear(i))
                .forEach(i -> System.out.printf("%d\t",i));

        System.out.printf("%n============================%n");

        IntStream.rangeClosed(1988,2100)
                .filter(i -> Year.of(i).isLeap())
                .forEach(i -> System.out.printf("%d\t",i));

    }
}
