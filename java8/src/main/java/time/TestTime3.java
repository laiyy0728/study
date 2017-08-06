package time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Author laiyy0728
 * @Date 2017/8/6 10:30
 */
public class TestTime3 {

    @Test
    public void test(){
        ZoneId zone = TimeZone.getDefault().toZoneId();
        System.out.println(LocalDate.of(2017, Month.APRIL, 18).atStartOfDay(zone));
        System.out.println(LocalDateTime.of(2017, Month.APRIL, 18, 13, 45).atZone(zone));
        System.out.println(Instant.now().atZone(zone));
    }

    @Test
    public void testZone(){
        System.out.println(ZoneId.of("Europe/Rome"));
        System.out.println(TimeZone.getDefault().toZoneId());
    }


    @Test
    public void testDateTimeFormatterBuilder(){
        // 创建自定义的格式器
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(LocalDate.now().format(dateTimeFormatter));
    }


    @Test
    public void test1(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));


//        System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_DATE_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_INSTANT));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_OFFSET_DATE));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_OFFSET_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_ORDINAL_DATE));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_WEEK_DATE));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
//        System.out.println(localDate.format(DateTimeFormatter.RFC_1123_DATE_TIME));
    }

}
