package christmas.domain.startegies;

import christmas.constants.MenuGroup;
import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.domain.Discount;

public class HolidayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (!discount.isHoliday()) {
            return 0;
        }
        // 주말 할인 : 메인 메뉴의 개수 * 2023 만큼 할인
        return discount.countCategory(MenuGroup.MAIN.name()) * Policy.HOLIDAY_DISCOUNT.getValue();
    }

    @Override
    public String name() {
        return Notification.HOLIDAY_DISCOUNT.getMessage();
    }
}