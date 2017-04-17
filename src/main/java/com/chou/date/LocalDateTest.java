package com.chou.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.MinguoDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/17.
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2017, 4, 17);
        final int year = localDate.getYear();
        final int monthValue = localDate.getMonthValue();
        final Month month = localDate.getMonth();
        final int dayOfMonth = localDate.getDayOfMonth();
        final int lengthOfMonth = localDate.lengthOfMonth();
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        final boolean leapYear = localDate.isLeapYear();

        System.out.println("LocalDate.toString\t"+localDate);
        System.out.printf("%s getYear()\t%d%n", "获取年",year, monthValue);
        System.out.printf("%s getMonthValue():\t%d%n","获取当前月份值",monthValue);
        System.out.printf("%s getMonth():\t%s%n","获取当前月份枚举名",month);
        System.out.printf("%s month.getDisplayName(TextStyle.FULL, Locale.CHINA):\t%s%n",
                "获取中文当前月名",month.getDisplayName(TextStyle.FULL, Locale.CHINA));
        System.out.printf("%s getDayOfMonth():\t%d%n","获取当前几号",dayOfMonth);
        System.out.printf("%s lengthOfMonth():\t%d%n","获取当月有几天",lengthOfMonth);
        System.out.printf("%s getDayOfWeek():\t%s%n","获取星期名",dayOfWeek);
        System.out.printf("%s dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA):\t%s%n",
                "获取中文星期名",dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA));
        System.out.printf("%s isLeapYear():\t%s%n","是否闰年",leapYear);


        System.out.println("============================================================");


        final LocalDate now = LocalDate.now();
//        使用TemporalField读取LocalDate的值
        int now_year = now.get(ChronoField.YEAR);
        int now_month = now.get(ChronoField.MONTH_OF_YEAR);
        int now_day = now.get(ChronoField.DAY_OF_MONTH);
        System.out.printf("当前时间 LocalDate.now()\t%s%n", now);
        System.out.printf("now.get(ChronoField.YEAR)\t%d%n", now_year);
        System.out.printf("now.get(ChronoField.MONTH_OF_YEAR)\t%d%n", now_month );
        System.out.printf("now.get(ChronoField.DAY_OF_MONTH)\t%d%n", now_day);

        System.out.println("============================================================");

        final MinguoDate minguoDate_now = MinguoDate.now();
        //Minguo ROC 第一年是1912年
        System.out.println(minguoDate_now);//Minguo ROC 106-04-17

        System.out.println("============================================================");


    }
}
