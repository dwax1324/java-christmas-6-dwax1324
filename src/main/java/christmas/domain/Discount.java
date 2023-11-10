package christmas.domain;

import christmas.constants.Holidays;
import christmas.constants.Policy;
import christmas.domain.startegies.DiscountStrategy;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Discount {

    private final LocalDate localDate;
    private final Menus menus;


    private Discount(Integer date, Menus menus) {
        this.localDate = LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), date);
        this.menus = menus;
    }

    public static Discount of(Integer date, Menus menus) {
        return new Discount(date, menus);
    }


    public List<Integer> calculate(DiscountStrategy... strategies) {
//        assert (Policy.HOLIDAY_DISCOUNT.getValue() == 2023 && Policy.MONTH.getValue() == 12); // double check
        Integer totalDiscount = 0;
        for (DiscountStrategy strategy : strategies) {
            totalDiscount += strategy.discount(this);
        }
        return null;
    }

//    public boolean isHoliday() {
//        return Date.isHoliday(this.localDate);
//    }

//    public long getDistance(Integer date) {
//        return Date.period(LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), this.date),
//                LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), date));
//    }

//    public Integer count(String name) {
//        return menus.countCategoryByCategoryName(name);
//    }
//
//    public boolean isSunday() {
//        return Date.isSunday(LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), this.date));
//    }

    public boolean isHoliday() {
        String dayOfWeek = localDate.getDayOfWeek().name();
        return Arrays.stream(Holidays.values()).anyMatch(day -> day.name().equals(dayOfWeek));
    }

    public boolean isSunday() {
        return localDate.getDayOfWeek().name().equals("SUNDAY");
    }

    public boolean isChristmasDay() {
        return localDate.getMonthValue() == Policy.MONTH.getValue()
                && localDate.getDayOfMonth() == Policy.CHRISTMAS_DAY.getValue();
    }
}