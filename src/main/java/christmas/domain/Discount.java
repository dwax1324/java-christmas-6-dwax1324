package christmas.domain;

import christmas.constants.Holidays;
import christmas.constants.Policy;
import christmas.domain.startegies.DiscountStrategy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Map.Entry<String, Integer>> calculateByStrategies(DiscountStrategy... strategies) {
        assert (isEventPeriod());
        List<Map.Entry<String, Integer>> discounts = new ArrayList<>();
        for (DiscountStrategy strategy : strategies) {
            discounts.add(Map.entry(strategy.name(), strategy.discount(this)));
        }
        return discounts.stream().filter(discount -> discount.getValue() > 0).collect(Collectors.toList());
    }

    private boolean isEventPeriod() {
        // Christmas event period: 2023.12.01 ~2023.12.31
        return localDate.getYear() == 2023 && localDate.getMonthValue() == 12;
    }

    public Integer getDate() {
        return localDate.getDayOfMonth();
    }

    public Integer countCategory(String name) {
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