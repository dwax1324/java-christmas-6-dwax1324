package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.Defaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventPlannerTest {
    final String VALIDMENUS = "a,b,c";
    @DisplayName("이벤트 플래너 생성이 문제 없이 되는지 확인")
    @Test
    void createNewEventPlanner(){
        assertThatCode(()->EventPlanner.of(3,VALIDMENUS)).doesNotThrowAnyException();
    }
    @DisplayName("이벤트 플래너 날짜가 유효 범위가 아님에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(ints={-1,0,123,124124})
    void dateNotValid(int date){
        assertThatThrownBy(() -> EventPlanner.of(date,VALIDMENUS)).isInstanceOf(IllegalArgumentException.class);
    }
}