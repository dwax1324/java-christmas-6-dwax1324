package christmas.Utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {

    @DisplayName("유효하지 수량에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-a",
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-42",
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림--",
            "시저샐러드-0",
    })
    void parseMenuInput(String input) {
        assertThatThrownBy(() -> Parser.parseMenuInput(input)).isInstanceOf(IllegalArgumentException.class);
    }
}