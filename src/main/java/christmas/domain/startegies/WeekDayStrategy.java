package christmas.domain.startegies;

import christmas.constants.MenuGroup;
import christmas.constants.Policy;
import christmas.domain.Discount;

public class WeekDayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (discount.isHoliday()) {
            return 0;
        }
        return discount.count(MenuGroup.MAIN.name()) * Policy.WEEKDAY_DISCOUNT.getValue();
    }
}