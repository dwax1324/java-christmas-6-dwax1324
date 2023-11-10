package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.domain.Discount;

public class SpecialStrategy implements DiscountStrategy {

    private boolean haveStar(Discount discount) {
        return discount.isSunday() || discount.isChristmasDay();
    }

    @Override
    public Integer discount(Discount discount) {
        if (!haveStar(discount)) {
            return 0;
        }
        return Policy.SPECIAL_DISCOUNT.getValue();
    }
}
