package christmas.Utils;

import christmas.constants.Holidays;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Date {
    public static boolean isHoliday(LocalDate time) {
        String dayOfWeek = time.getDayOfWeek().name();
        return Arrays.stream(Holidays.values()).anyMatch(r -> r.name().equals(dayOfWeek));
    }

    public static long period(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }
}