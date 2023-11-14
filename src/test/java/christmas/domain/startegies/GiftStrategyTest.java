package christmas.domain.startegies;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.Policy;
import christmas.domain.Discount;
import christmas.domain.menu.Menus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftStrategyTest {
    @DisplayName("증정 할인이 가능한 가격에 대한 테스트")
    @Test
    void canGiftDiscountTest() {
        Menus menus = Menus.from("티본스테이크-10");
        assertThat(new GiftStrategy().discount(Discount.of(1, menus))).isEqualTo(Policy.CHAMPAGNE.getValue());
    }

    @DisplayName("증정 할인이 불가능한 가격에 대한 테스트")
    @Test
    void cantGiftDiscountTest() {
        Menus menus = Menus.from("양송이수프-1");
        assertThat(new HolidayStrategy().discount(Discount.of(1, menus))).isEqualTo(0);
    }
}