package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import christmas.domain.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DesignatedDayStrategyTest {

    @DisplayName("디데이 할인이 정상적으로 작동하는지 확인")
    @Test
    void checkDdayDiscount() {
        Menus menus = Menus.from("제로콜라-1");
        assertThat(new DesignatedDayStrategy().discount(Discount.of(1, menus))).isEqualTo(1000);
        assertThat(new DesignatedDayStrategy().discount(Discount.of(2, menus))).isEqualTo(1100);
        assertThat(new DesignatedDayStrategy().discount(Discount.of(24, menus))).isEqualTo(3300);
        assertThat(new DesignatedDayStrategy().discount(Discount.of(25, menus))).isEqualTo(3400);

    }

}