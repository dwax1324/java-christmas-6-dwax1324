package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolidayStrategyTest {

    @DisplayName("주말 할인이 적용 되는 날짜가 잘 적용되는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void checkHolidayDiscount(int input) {
        assertThat(new HolidayStrategy().discount(Discount.of(input, 2))).isEqualTo(2023);
    }

    @DisplayName("주말 할인이 적용 안되는 날짜에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 10, 11, 12})
    void notHolidayDiscount(int input) {
        assertThat(new HolidayStrategy().discount(Discount.of(input, 2))).isEqualTo(0);
    }

}