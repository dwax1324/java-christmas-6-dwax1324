package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.domain.Discount;

public class DesignatedDayStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (discount.getDate() > 25) {
            return 0;
        }
        final int designatedDay = discount.getDate() - 1;
        return Policy.DDAY_INITIAL_DISCOUNT.getValue()
                + designatedDay * Policy.DDAY_AFTER_DISCOUNT.getValue();
    }
}