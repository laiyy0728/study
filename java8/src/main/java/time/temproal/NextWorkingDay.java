package time.temproal;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK)); // 读取当前日期
        int dayNext = 1; // 正常情况下，加一天
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            dayNext = 3; // 如果是周五，加三天
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            dayNext = 2; // 如果是周六，加两天
        }
        return temporal.plus(dayNext, ChronoUnit.DAYS);
    }
}
