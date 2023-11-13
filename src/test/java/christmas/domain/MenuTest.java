package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constants.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @DisplayName("저장된 이름 이외의 이름에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"양고기수프", "싸이버거", "불고기피자"})
    void createMenuByInvalidMenuName(String input) {
        assertThatThrownBy(() -> Menu.of(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("저장된 메뉴 이름에 대한 메뉴 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "티본스테이크", "해산물파스타", "초코케이크", "제로콜라"})
    void createMenuByValidMenuName(String menuName) {
        assertThatCode(() -> Menu.of(menuName)).doesNotThrowAnyException();
    }


    @DisplayName("카테코리 이름이 주어졌을 때 카테고리 이름이 동일한지 확인하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void checkCategory(String input) {
        Menu menu = Menu.of(input);
        assertThat(menu.isCategoryByCategoryName(MenuGroup.BEVERAGE.name())).isEqualTo(true);
    }

    @DisplayName("두 메뉴의 이름이 equals를 통해 같은지 확인하는 테스트")
    @Test
    void Given_TwoSameMenu_When_equals_Then_ReturnsTrue() {
        // given
        Menu menu1 = Menu.of("제로콜라");
        Menu menu2 = Menu.of("제로콜라");
        // when
        boolean isSame = menu1.equals(menu2);
        // then
        assertThat(isSame).isEqualTo(true);
    }

    @DisplayName("두 메뉴의 가격이 같은지 확인하는 테스트")
    @Test
    void Given_TwoSameMenu_When_ComparedPrice_Then_PriceIsSame() {
        // given
        Menu menu1 = Menu.of("제로콜라");
        Menu menu2 = Menu.of("제로콜라");
        // when
        int price1 = menu1.getPrice();
        int price2 = menu2.getPrice();
        // then
        assertThat(price1).isEqualTo(price2);
    }

    @DisplayName("두 메뉴의 이름이 같은지 getName을 통해 확인하는 테스트")
    @Test
    void Given_TwoSameMenu_When_ComparedName_Then_NameIsSame() {
        // given
        Menu menu1 = Menu.of("제로콜라");
        Menu menu2 = Menu.of("제로콜라");
        // when
        String name1 = menu1.getName();
        String name2 = menu2.getName();
        // then
        assertThat(name1).isEqualTo(name2);
    }
}