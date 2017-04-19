package com.chou.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

/**
 * Created by Administrator on 2017/4/18.
 */
public class PeriodTest {
    public static void main(String[] args) {
        Period tenDays = Period.between(LocalDate.of(2010, 7, 1),
                LocalDate.of(2017, 4, 18));
        System.out.println(tenDays);
        System.out.printf("getYears()\t%d%n", tenDays.getYears());
        System.out.printf("getMonths()\t%d%n", tenDays.getMonths());
        System.out.printf("getDays()\t%d%n", tenDays.getDays());

        System.out.println(tenDays.get(ChronoUnit.DAYS));
        System.out.printf("toTotalMonths()\t%d%n", tenDays.toTotalMonths());

        LocalDate date1 = LocalDate.of(2017, 2, 22), date2 = LocalDate.of(2017, 4, 18);
        Period p = Period.ofDays((int)ChronoUnit.DAYS.between(date1, date2));
        System.out.println("date1 + p: "+date1.plus(p));
        System.out.println("date2 - p: "+date2.minus(p));

        printAgeAndBirthday(1989, 2, 22);

        printBirthdayFromPeriod(28, 1, 27);
    }

    //TODO 根据总天数可以算出正确生日，根据年月日算出的生日是错误的。
    private static void printBirthdayFromPeriod(int years, int months, int days) {
        final Period period = Period.of(years, months, days);
        final LocalDate now = LocalDate.now();
        final LocalDate birthday = now.minus(28, ChronoUnit.YEARS)
                .minus(1, ChronoUnit.MONTHS)
                .minus(27, ChronoUnit.DAYS);

        System.out.println("your birthday is : "+ birthday);
        System.out.println("your birthday is : "+ now.minusYears(28).minusMonths(1).minusDays(27));
        System.out.println("your birthday is : "+ now.minus(period));
        System.out.println("your birthday is : "+period.subtractFrom(now));
        System.out.println("your birthday is : "+ now.minus(Period.ofDays(10282)));
    }

    private static void printAgeAndBirthday(int year, int month, int dayOfMonth) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(year, month, dayOfMonth);

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.printf("You are %d years, %d months, and %d days old. (%d days total)%n",
                p.getYears(), p.getMonths(), p.getDays(), p2);

        LocalDate nextBDay = birthday.withYear(today.getYear());

        //If your birthday has occurred this year already, add 1 to the year.
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        Period p_1 = Period.between(today, nextBDay);
        long p_2 = ChronoUnit.DAYS.between(today, nextBDay);
        System.out.printf("There are %d months, and %d days until your next birthday. (%d total)%n",
                p_1.getMonths(), p_1.getDays(), p_2);
    }
}
