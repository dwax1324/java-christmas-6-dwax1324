package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenusTest {

    @DisplayName("메뉴 모음 생성 테스트")
    @Test
    void of() {
        assertThatCode(() -> Menus.from("시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1")).doesNotThrowAnyException();
    }

    @DisplayName("메뉴 모음의 총 가격이 정확히 반환하는지 확인")
    @CsvSource(value = {
            "양송이수프-10: 60000",
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1: 102000",
            "티본스테이크-20: 1100000"
    }, delimiter = ':')
    @ParameterizedTest
    void Given_Menus_When_CalculateTotalPrice_Then_TotalPriceIsExpected(String menu, int price) {
        // given
        Menus menus = Menus.from(menu);
        // when
        final int trial = menus.totalPrice();
        // then
        assertThat(trial).isEqualTo(price);
    }
}