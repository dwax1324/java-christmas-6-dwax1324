package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.MenuGroup;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;
import christmas.dto.MenusDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenusTest {
    @DisplayName("유효한 메뉴 모음 생성 테스트")
    @Test
    void createValidMenuTest() {
        assertThatCode(() -> Menus.from("시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1")).doesNotThrowAnyException();
    }

    @DisplayName("메뉴판에 없는 메뉴 모음 생성에 대한 예외 처리")
    @Test
    void createInvalidMenuTest() {
        assertThatThrownBy(() -> Menus.from("싸이버거-1,불고기버거-1,반반무많이-1")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 모음의 총 가격을 정확히 반환하는지 확인")
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
        int trial = menus.totalPrice();
        // then
        assertThat(trial).isEqualTo(price);
    }

    @DisplayName("메뉴 모음에서 같은 카테코리를 수를 잘 반환하는지에 대한 테스트")
    @ParameterizedTest
    @CsvSource(value = {
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1 : 2",
            "티본스테이크-20 : 20",
            "바비큐립-14: 14",
            "초코케이크-20: 0",
            "양송이수프-20: 0",
            "크리스마스파스타-6 : 6"

    }, delimiter = ':')
    void Given_MainMenuAndQuantity_When_CountCategoryByName_Then_MainCountIsGivenQuantity(String menuName,
                                                                                          Integer quantity) {
        // given
        Menus menus = Menus.from(menuName);
        // when
        int mainCount = menus.countCategoryByCategoryName(MenuGroup.MAIN.name());
        // then
        assertThat(mainCount).isEqualTo(quantity);
    }

    @DisplayName("메뉴 모음에서 같은 이름의 메뉴의 개수를 잘 반환하는지 테스트")
    @Test
    void Given_Menus_When_CountMenu_Then_ReturnsGivenQuantity() {
        // given
        Menus menus = Menus.from("시저샐러드-10, 티본스테이크-7, 크리스마스파스타-1, 제로콜라-1, 아이스크림-1");
        // when
        int ten = menus.count(Menu.of("시저샐러드"));
        int seven = menus.count(Menu.of("티본스테이크"));
        int one = menus.count(Menu.of("크리스마스파스타"));
        // then
        assertThat(ten).isEqualTo(10);
        assertThat(seven).isEqualTo(7);
        assertThat(one).isEqualTo(1);
    }

    @DisplayName("메뉴 모음에서 dto로 잘 변환하는지 확인하는 테스트")
    @Test
    void Given_Menus_When_ToDto_Then_ReturnTypeIsMenusDto() {
        // given
        Menus menus = Menus.from("티본스테이크-10");
        // when
        // then
        assertThat(menus.toDto()).isInstanceOf(MenusDto.class);
    }
}