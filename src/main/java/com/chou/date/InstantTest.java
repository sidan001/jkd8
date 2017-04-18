package com.chou.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

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
    }
}
