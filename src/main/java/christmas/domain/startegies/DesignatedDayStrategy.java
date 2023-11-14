package christmas.domain.startegies;

import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.domain.Discount;

public class DesignatedDayStrategy implements DiscountStrategy {

    @Override
    public Integer discount(Discount discount) {
        if (discount.getDate() > Policy.CHRISTMAS_DAY.getValue()) {
            return 0;
        }
        // 디데이 할인: 1000 + (날짜 - 1) * 100 만큼 할인 (적용 기간: 2023.12.01 ~ 2023.12.25)
        int dDayDiscount = discount.getDate() - 1;
        return Policy.DDAY_INITIAL_DISCOUNT.getValue()
                + dDayDiscount * Policy.DDAY_AFTER_DISCOUNT.getValue();
    }

    @Override
    public String name() {
        return Notification.DESIGNATED_DAY_DISCOUNT.getMessage();
    }
}