package christmas.domain.startegies;

import christmas.domain.Discount;

public interface DiscountStrategy {
    Integer discount(Discount discount);
}