package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.domain.Discount;

public class SpecialStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (!haveStar(discount)) {
            return 0;
        }
        return Policy.SPECIAL_DISCOUNT.getValue();
    }

    @Override
    public String name() {
        return Notification.SPECIAL_DAY_DISCOUNT.getMessage();
    }

    private boolean haveStar(Discount discount) {
        return discount.isSunday() || discount.isChristmasDay();
    }
}
