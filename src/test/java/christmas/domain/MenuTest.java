package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}