package com.chou.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/17.
 */
public class DateTest {
    public static void main(String[] args) {
        //年1900+117 --> 2017,月份从0开始
        Date date_BeforeJdk8 = new Date(117,3,17);
        //CST – Central Standard Time
        System.out.println(date_BeforeJdk8);//Wed May 17 00:00:00 CST 2017

        //Date和Calendar类都是可以变的

    }
}
