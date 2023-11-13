package christmas.utils;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ParserTest {
    @DisplayName("유효하지 않은 형식에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-a",
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-42",
            "시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림--",
            "시저샐러드-0",
            ",,,",
            "시저샐러드-0011",
    })
    void parseInvalidMenuInput(String input) {
        assertThatThrownBy(() -> Parser.parseMenuInput(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 형식에 대한 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프-1,타파스-1,시저샐러드-1,티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1,초코케이크-1,아이스크림-1,제로콜라-1,레드와인-1,샴페인-1",
            "양송이수프-20",
            "양송이수프-1",
            "abasbewrqwrw-12",
            "qwrqrw - 1",
    })
    void parseValidMenuInput(String input) {
        assertThatCode(() -> Parser.parseMenuInput(input)).doesNotThrowAnyException();
    }
}