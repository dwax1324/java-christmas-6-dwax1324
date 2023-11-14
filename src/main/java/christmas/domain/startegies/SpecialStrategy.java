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
        // 특별 할인: 별표가 있는 날(모든 일요일과 크리스마스) 1000원 할인
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
