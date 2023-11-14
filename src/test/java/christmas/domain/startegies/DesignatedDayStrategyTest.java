package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DesignatedDayStrategyTest {
    @DisplayName("디데이 할인이 정상적으로 작동하는지에 대한 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1 , 1000", "2 , 1100", "24 , 3300", "25, 3400"})
    void checkDDayDiscount(Integer date, Integer discount) {
        Menus menus = Menus.from("아이스크림-1");
        assertThat(new DesignatedDayStrategy().discount(Discount.of(date, menus))).isEqualTo(discount);
    }
}