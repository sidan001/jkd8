package com.chou.date;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Administrator on 2017/4/17.
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        //创建LocalTime并读取其值
        LocalTime time = LocalTime.of(13, 45, 20);
        final LocalTime now_time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.printf("%d:%d:%d%n", hour, minute, second);

        System.out.println("============================================================");

        //LocalDate和LocalTime都可以通过解析代表它们的字符串创建
        LocalDate parseDate = LocalDate.parse("2014-03-18");
        LocalTime parseTime = LocalTime.parse("13:45:20");
        System.out.println(parseDate);
        System.out.println(parseTime);
    }
}
