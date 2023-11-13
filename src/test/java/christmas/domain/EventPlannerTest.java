package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.dto.EventPlannerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventPlannerTest {
    final Menus VALID_MENUS = Menus.from("티본스테이크-5");

    @DisplayName("이벤트 플래너 생성이 문제 없이 되는지 확인")
    @Test
    void createNewEventPlanner() {
        assertThatCode(() -> EventPlanner.of(3, VALID_MENUS)).doesNotThrowAnyException();
    }

    @DisplayName("이벤트 플래너 날짜가 유효 범위가 아님에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 123, 124124})
    void createNewEventPlannerWithInvalidDate(int date) {
        assertThatThrownBy(() -> EventPlanner.of(date, VALID_MENUS)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이벤트 플래너 날짜가 유효 범위가 아님에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 123, 124124})
    void validateDateTest(int date) {
        assertThatThrownBy(() -> EventPlanner.validateDate(date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이벤트 플래너가 dto로 잘 변환되는지 테스트")
    @Test
    void toDtoTest() {
        EventPlanner eventPlanner = EventPlanner.of(3, VALID_MENUS);
        assertThat(eventPlanner.toDto()).isInstanceOf(EventPlannerDto.class);
    }
}