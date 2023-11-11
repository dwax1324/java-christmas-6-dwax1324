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
    void nameNotExist(String input) {
        assertThatThrownBy(() -> Menu.of(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 생성 테스트")
    @Test
    void of() {
        assertThatCode(() -> Menu.of("양송이수프")).doesNotThrowAnyException();
    }


    @DisplayName("카테코리 이름이 주어졌을 때 카테고리 이름이 동일한지 확인하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void checkCategory(String input) {
        Menu menu = Menu.of(input);
        assertThat(menu.isCategoryByCategoryName(MenuGroup.BEVERAGE.name())).isEqualTo(true);
    }

    @DisplayName("두 메뉴가 같은지 확인하는 기능 테스트")
    @Test
    void hasSameName() {
        Menu menu1 = Menu.of("제로콜라");
        Menu menu2 = Menu.of("제로콜라");
        assertThat(menu1.equals(menu2)).isEqualTo(true);
    }
}