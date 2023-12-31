package christmas.domain;

import christmas.constants.Policy;
import christmas.constants.messages.Error;
import christmas.constants.messages.Notification;
import christmas.domain.menu.Menus;
import christmas.domain.startegies.DesignatedDayStrategy;
import christmas.domain.startegies.GiftStrategy;
import christmas.domain.startegies.HolidayStrategy;
import christmas.domain.startegies.SpecialStrategy;
import christmas.domain.startegies.WeekDayStrategy;
import christmas.dto.EventPlannerDto;
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

    public static EventPlanner of(Integer date, Menus menus) {
        return new EventPlanner(date, menus);
    }

    private List<Map.Entry<String, Integer>> getDiscount() {
        if (menus.totalPrice() < Policy.MIN_COST_FOR_DISCOUNT.getValue()) {
            return List.of(); // 할인 적용 X
        }
        Discount discount = Discount.of(date, menus);
        return discount.calculateByStrategies(new HolidayStrategy(), new DesignatedDayStrategy(),
                new SpecialStrategy(), new WeekDayStrategy(), new GiftStrategy());
    }

    private boolean isGift() {
        return menus.totalPrice() >= Policy.MIN_COST_FOR_GIFT.getValue();
    }

    private String getBadge(Integer afterDiscount) {
        if (afterDiscount >= Policy.SANTA.getValue()) {
            return Notification.SANTA.getMessage();
        }
        if (afterDiscount >= Policy.TREE.getValue()) {
            return Notification.TREE.getMessage();
        }
        if (afterDiscount >= Policy.STAR.getValue()) {
            return Notification.STAR.getMessage();
        }
        return Notification.NONE.getMessage();
    }

    private Integer getTotalDiscount(List<Map.Entry<String, Integer>> discount) {
        return discount.stream().mapToInt(Entry::getValue).sum();
    }

    public EventPlannerDto toDto() {
        int discounted = getTotalDiscount(getDiscount()); // 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
        int afterDiscount = menus.totalPrice() - discounted; // 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
        return new EventPlannerDto(date, menus.toDto(), menus.totalPrice(), isGift(), getDiscount(), discounted,
                afterDiscount, getBadge(afterDiscount));
    }

    public static void validateDate(Integer date) {
        try {
            LocalDate.of(Policy.YEAR.getValue(), Policy.MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(Error.DATE.getMessage());
        }
    }
}