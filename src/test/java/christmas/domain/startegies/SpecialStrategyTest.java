package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialStrategyTest {
    @DisplayName("주말 할인이 적용 되는 날짜에 대한 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    void checkHolidayDiscount(int input) {
        Menus menus = Menus.from("초코케이크-2");
        assertThat(new SpecialStrategy().discount(Discount.of(input, menus))).isEqualTo(1000);
    }

    @DisplayName("주말 할인이 적용 안되는 날짜에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {1, 30, 6, 13, 26, 4})
    void noDiscount(int input) {
        Menus menus = Menus.from("초코케이크-2");
        assertThat(new SpecialStrategy().discount(Discount.of(input, menus))).isEqualTo(0);
    }
}