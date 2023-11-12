package christmas.domain;

import christmas.constants.Holidays;
import christmas.constants.Policy;
import christmas.domain.startegies.DiscountStrategy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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


    public List<Map.Entry<String, Integer>> calculate(DiscountStrategy... strategies) {
        List<Map.Entry<String, Integer>> discounts = new ArrayList<>();
        for (DiscountStrategy strategy : strategies) {
            discounts.add(Map.entry(strategy.name(), strategy.discount(this)));
        }
        if (menus.totalPrice() < 10000 || discounts.stream().allMatch(r -> r.getValue() == 0)) {
            return null;
        }
        return discounts.stream().filter(r -> r.getValue() > 0).toList();
    }

    public Integer getDate() {
        return localDate.getDayOfMonth();
    }

    public Integer count(String name) {
        return menus.countCategoryByCategoryName(name);
    }

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