package time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Java 8 新的日期 API 测试
 * Created by laiyy
 * Date 2017/8/4.
 */
public class TestTime1 {


    @Test
    public void testDurationAndPeriod(){
        Duration duration = Duration.ofMinutes(3);
        Duration of = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(duration);
        System.out.println(of);


        Period period = Period.ofDays(10);
        Period period1 = Period.ofWeeks(3);
        Period of1 = Period.of(2, 6, 1);
        System.out.println(period);
        System.out.println(period1);
        System.out.println(of1);
    }

    @Test
    public void testDuration(){
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.now();
        System.out.println(Duration.between(instant, instant1)); // 持续时间
    }

    @Test
    public void testInstant(){
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(2, -1_000_000_000));
        System.out.println(Instant.now().get(ChronoField.DAY_OF_YEAR)); // 抛出异常
    }

    @Test
    public void testLocalDateTime(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(2017, 8, 4, 16, 31, 30);
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        System.out.println(date.atTime(13, 45, 20));
        System.out.println(time.atDate(date));
    }

    @Test
    public void testLocalTime(){
        LocalTime time = LocalTime.of(15, 56, 30);
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    @Test
    public void testLocalDate1(){
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_YEAR));
    }

    @Test
    public void testLocalDate(){
        LocalDate date = LocalDate.of(2017, 8, 4);  // 声明一个指定日期的对象
        System.out.println(date);
        int year = date.getYear(); // 获取日期的年份
        System.out.println(year);
        Month month = date.getMonth(); // 获取日期的月份（枚举）
        System.out.println(month.getValue()); // 获取日期的月份（数字）
        int day = date.getDayOfMonth(); // 获取指定日期是该月的第几天
        System.out.println(day);
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // 指定日期是所在周的第几天（枚举）
        System.out.println(dayOfWeek.getValue());// 指定日期是所在周的第几天（数字）
        int lengthOfMonth = date.lengthOfMonth(); // 指定日期所在的月份有几天
        System.out.println(lengthOfMonth);
        boolean leapYear = date.isLeapYear(); // 日期所在年份是否是闰年
        System.out.println(leapYear);
    }


}
