package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.domain.Discount;

public class HolidayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (!discount.isHoliday()) {
            return 0;
        }
        return Policy.HOLIDAY_DISCOUNT.getValue();
    }
}