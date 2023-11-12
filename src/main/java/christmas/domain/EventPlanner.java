package christmas.domain;

import christmas.constants.Policy;
import christmas.constants.messages.Error;
import christmas.domain.startegies.DesignatedDayStrategy;
import christmas.domain.startegies.HolidayStrategy;
import christmas.domain.startegies.SpecialStrategy;
import christmas.domain.startegies.WeekDayStrategy;
import christmas.dto.EventPlannerDto;
import christmas.dto.MenusDto;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
        if (menus.totalPrice() < 10000) {
            return null;
        }
        Discount discount = Discount.of(date, menus);
        List<Entry<String, Integer>> discountResult = discount.calculateByStrategies(new HolidayStrategy(),
                new DesignatedDayStrategy(), new SpecialStrategy(),
                new WeekDayStrategy());
        if (isGift()) {
            discountResult.add(Map.entry("증정 이벤트", 25000));
        }
        return discountResult;
    }

    private boolean isGift() {
        return menus.totalPrice() >= 120000;
    }

    private String getBadge(Integer afterDiscount) {
        if (afterDiscount >= 20000) {
            return "산타";
        }
        if (afterDiscount >= 10000) {
            return "트리";
        }
        if (afterDiscount >= 5000) {
            return "별";
        }
        return "없음";
    }

    private Integer getBeforeDiscount() {
        return menus.totalPrice();
    }


    private Integer getTotalDiscount(List<Map.Entry<String, Integer>> discount) {
        if (discount == null) {
            return 0;
        }
        return discount.stream().mapToInt(Entry::getValue).sum();
    }

    public EventPlannerDto toDto() {
        int discounted = getTotalDiscount(getDiscount());
        // 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        int totalBenefit = 0;
        if (isGift()) {
            totalBenefit += 25000;
        }
        int afterDiscount = menus.totalPrice() - discounted;
        totalBenefit += discounted;
        return new EventPlannerDto(date, menus.toDto(), menus.totalPrice(), isGift(), getDiscount(), totalBenefit,
                afterDiscount, getBadge(afterDiscount));
    }

    public static void validateDate(Integer date) {
        try {
            LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(Error.DATE.getMessage());
        }
    }

    public static void validateBeverage(Menus menus) {
        Set<String> categories = new HashSet<>();
    }

    public static EventPlanner of(Integer date, Menus menus) {
        return new EventPlanner(date, menus);
    }
}
