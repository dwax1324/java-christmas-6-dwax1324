package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WeekDayStrategyTest {
    @DisplayName("평일 할인이 적용 되는 날짜에 대한 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 10, 11, 12, 18, 28})
    void notWeekdayDiscount(int input) {
        Menus menus = Menus.from("초코케이크-2");
        assertThat(new WeekDayStrategy().discount(Discount.of(input, menus))).isEqualTo(2023 * 2);
    }

    @DisplayName("평일 할인이 적용이  안되는 날짜에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void checkWeekdayDiscount(int input) {
        Menus menus = Menus.from("초코케이크-2");
        assertThat(new WeekDayStrategy().discount(Discount.of(input, menus))).isEqualTo(0);
    }
}