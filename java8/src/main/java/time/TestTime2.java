package time;

import org.junit.Test;
import time.temproal.NextWorkingDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * 操纵、解析、格式化日期
 * Created by laiyy
 * Date 2017/8/4.
 */
public class TestTime2 {

    @Test
    public void testNextWorkingDay() {
        LocalDate localDate = LocalDate.now();
        LocalDate with = localDate.with(new NextWorkingDay());
        System.out.println(with);
    }

    @Test
    public void testTemporalAdjuster(){
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        LocalDate nextSunday = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));// 下周日
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());// 本月的最后一天
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());// 本月第一天
        LocalDate firstDayOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth());// 下月第一天
        LocalDate firstDayOfNextYear = localDate.with(TemporalAdjusters.firstDayOfNextYear());// 下一年第一天
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());// 本年第一天
        LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());// 本年最后一天
        LocalDate firstSundayInMonth = localDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));// 本月第一个周日
        LocalDate previousSunday = localDate.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));// 上个周日
        System.out.println(nextSunday);
        System.out.println(lastDayOfMonth);
        System.out.println(firstDayOfMonth);
        System.out.println(firstDayOfNextMonth);
        System.out.println(firstDayOfNextYear);
        System.out.println(firstDayOfYear);
        System.out.println(firstSundayInMonth);
        System.out.println(lastDayOfYear);
        System.out.println(previousSunday);
    }

    @Test
    public void test2() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        LocalDate date = localDate.plusWeeks(1);
        LocalDate date1 = date.minusYears(3);
        LocalDate plus = date1.plus(6, ChronoUnit.MONTHS);
        System.out.println(date);
        System.out.println(date1);
        System.out.println(plus);

    }

    @Test
    public void test1() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        LocalDate withYear = localDate.withYear(2011);
        LocalDate withDayOfMonth = withYear.withDayOfMonth(25);
        LocalDate date = withDayOfMonth.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(withYear);
        System.out.println(withDayOfMonth);
        System.out.println(date);
    }


}
