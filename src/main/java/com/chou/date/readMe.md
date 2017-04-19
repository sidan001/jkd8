### DayOfWeek and Month Enums

#### DayOfWeek

The `DayOfWeek` enum consists of seven constants that describe the days of the week: MONDAY through SUNDAY.
The integer values of the `DayOfWeek` constants range from 1 (Monday) through 7 (Sunday).

#### Month

The `Month` enum includes constants for the twelve months, JANUARY through DECEMBER.
As with the `DayOfWeek` enum, the `Month` enum is strongly typed, 
and the integer value of each constant corresponds to the ISO range from 1 (January) through 12 (December).
Using the defined constants (Month.SEPTEMBER) makes your code more readable.

### Date Classes
#### LocalDate
A `LocalDate` represents a _year-month-day_ in the ISO calendar and is useful for representing a date
without a time. 

#### YearMonth
The `YearMonth` class represents the month of a specific year.
#### MonthDay
The `MonthDay` class represents the day of a particular month, such as _New Year's Day on January 1_.
#### Year
The `Year` class represents a year.

### Date and Time Classes

#### LocalTime
The `LocalTime` class is similar to the other classes whose names are prefixed with Local, 
**but deals in time only.** 
This class is useful for representing human-based time of day, such as movie times,
or the opening and closing times of the local library.
 
The `LocalTime` class does not store time zone or daylight saving time information.

#### LocalDateTime

handles both date and time, without a time zone.

 This class is used to represent date (month-day-year) together with time (hour-minute-second-nanosecond)
  and is, in effect, a combination of `LocalDate` with `LocalTime`. 

###　Time Zone and Offset Classes
A _time zone_ is a region of the earth where the same standard time is used. 
Each time zone is described by an identifier and usually has the format _region/city (Asia/Tokyo)_
and an offset from _Greenwich/UTC_ time.
For example, the offset for Tokyo is _+09:00_.

#### ZoneId and ZoneOffset

The Date-Time API provides two classes for specifying a time zone or an offset:

* `ZoneId` specifies a time zone identifier and provides rules for converting between an `Instant` and a `LocalDateTime`.
* `ZoneOffset` specifies a time zone offset from _Greenwich/UTC_ time.

####　The Date-Time Classes
The Date-Time API provides three temporal-based classes that work with time zones:

* `ZonedDateTime` handles a date and time with a corresponding time zone with a time zone offset from _Greenwich/UTC_.
* `OffsetDateTime` handles a date and time with a corresponding time zone offset from _Greenwich/UTC_, without a time zone ID.
* `OffsetTime` handles time with a corresponding time zone offset from _Greenwich/UTC_, without a time zone ID.

    
**ZonedDateTime**
    
 The `ZonedDateTime` class, in effect, combines the `LocalDateTime` class with the `ZoneId` class. 
 
 It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond)
 with a time zone (region/city, such as _Europe/Paris_).

```
 2017-04-19T15:05:16.750+08:00[Asia/Shanghai]
 ```

**OffsetDateTime**

The `OffsetDateTime` class, in effect, combines the `LocalDateTime` class with the `ZoneOffset` class.

It is used to represent a full date (year, month, day) and time (hour, minute, second, nanosecond) 
with an offset from Greenwich/UTC time (+/-hours:minutes, such as +06:00 or -08:00).

```
 2017-04-19T15:05:16.751+08:00
 ```

**OffsetTime**

The `OffsetTime` class, in effect, combines the `LocalTime` class with the `ZoneOffset` class. 

It is used to represent time (hour, minute, second, nanosecond) 
with an offset from Greenwich/UTC time (+/-hours:minutes, such as +06:00 or -08:00).

The `OffsetTime` class is used in the same situations as the `OffsetDateTime` class, 
but when tracking the date is not needed.

### Instant Class

 Represents the start of a nanosecond on the timeline. 
 This class is useful for generating a time stamp to represent machine time.
 
 A value returned from the Instant class counts time beginning from 
 the first second of January 1, 1970 (_1970-01-01T00:00:00Z_) 
 also called the [EPOCH](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#EPOCH)
 
 ```
 2017-04-18T07:24:09.932Z 
 ```
 **The Instant class does not work with human units of time, such as `years`, `months`, or `days`.**