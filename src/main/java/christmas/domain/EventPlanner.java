package christmas.domain;

import christmas.constants.Policy;
import christmas.domain.startegies.DesignatedDayStrategy;
import christmas.domain.startegies.HolidayStrategy;
import christmas.domain.startegies.SpecialStrategy;
import christmas.domain.startegies.WeekDayStrategy;
import christmas.dto.EventPlannerDto;
import christmas.dto.MenusDto;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventPlanner {

    private final Integer date;
    private final Menus menus;

    private EventPlanner(Integer date, Menus menus) {
        validateDate(date);
        this.date = date;
        this.menus = menus;
    }


    private MenusDto getMenusDto() {
        return menus.toDto();
    }

    private List<Map.Entry<String, Integer>> getDiscount() {
        Discount discount = Discount.of(date, menus);
        List<Map.Entry<String, Integer>> discounts = discount.calculate(new HolidayStrategy(),
                new DesignatedDayStrategy(), new SpecialStrategy(), new WeekDayStrategy());
        return discounts;
    }

    private boolean isGift() {
        return menus.totalPrice() >= 120000;
    }

    private String getBadge() {
        int price = menus.totalPrice();
        if (price >= 20000) {
            return "산타";
        }
        if (price >= 10000) {
            return "트리";
        }
        if (price >= 5000) {
            return "별";
        }
        return "없음";
    }

    public EventPlannerDto toDto() {
        Integer moneyBeforeDiscount = menus.totalPrice();
        // getMenusDto / menus.totalPrice() / boolean / DiscountsDto / totalprice-totalDiscount / String Badge
//        menus / price / boolean / Discounts / priceAfterDiscount / Badge
        List<Map.Entry<String, Integer>> discount = getDiscount();
        int discounted = discount.stream().mapToInt(Entry::getValue).sum();
        System.err.println(discounted);
        return new EventPlannerDto(menus.toDto(), menus.totalPrice(), isGift(), getDiscount(),
                menus.totalPrice() - discounted, getBadge());
    }

    public static void validateDate(Integer date) {
        try {
            LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR]: 유효한 날짜를 입력해주세요");
        }
    }

    public static EventPlanner of(Integer date, Menus menus) {
        return new EventPlanner(date, menus);
    }
}
