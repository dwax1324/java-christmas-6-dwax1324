package christmas.domain.startegies;

import christmas.constants.MenuGroup;
import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.domain.Discount;

public class WeekDayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (discount.isHoliday()) {
            return 0;
        }
        // 평일 할인: 디저트 메뉴 개수 * 2023
        return discount.countCategory(MenuGroup.DESSERT.name()) * Policy.WEEKDAY_DISCOUNT.getValue();
    }

    @Override
    public String name() {
        return Notification.WEEKDAY_DISCOUNT.getMessage();
    }
}