package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.domain.Discount;

public class GiftStrategy implements DiscountStrategy {
    @Override
    public Integer discount(Discount discount) {
        if (discount.getTotalPrice() >= Policy.MIN_COST_FOR_GIFT.getValue()) {
            // 증정 할인: 증정 물품의 가격만큼 할인
            return Policy.CHAMPAGNE.getValue();
        }
        return 0;
    }

    @Override
    public String name() {
        return Notification.GIFT_EVENT.getMessage();
    }
}