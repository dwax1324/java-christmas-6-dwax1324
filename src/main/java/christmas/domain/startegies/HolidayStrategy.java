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
        return discount.count(MenuGroup.MAIN.name()) * Policy.HOLIDAY_DISCOUNT.getValue();
    }

    @Override
    public String name() {
        return Notification.HOLIDAY_DISCOUNT.getMessage();
    }
}