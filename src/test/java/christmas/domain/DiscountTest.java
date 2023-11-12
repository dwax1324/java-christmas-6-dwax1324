package christmas.domain;

import christmas.domain.startegies.DesignatedDayStrategy;
import christmas.domain.startegies.HolidayStrategy;
import christmas.domain.startegies.SpecialStrategy;
import christmas.domain.startegies.WeekDayStrategy;
import org.junit.jupiter.api.Test;

class DiscountTest {
    @Test
    void test() {
        Menus menus = Menus.from("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Discount discount = Discount.of(3, menus);
        discount.calculate(new DesignatedDayStrategy(), new HolidayStrategy(), new SpecialStrategy(),
                new WeekDayStrategy());
    }
}