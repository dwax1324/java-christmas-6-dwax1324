package christmas.domain.startegies;

import christmas.constants.MenuGroup;
import christmas.constants.Policy;
import christmas.domain.Discount;

public class HolidayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (!discount.isHoliday()) {
            return 0;
        }
        return discount.count(MenuGroup.DESSERT.name()) * Policy.HOLIDAY_DISCOUNT.getValue();
    }
}