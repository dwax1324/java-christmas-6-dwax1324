package christmas.Utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("휴일이 아닌 날짜에 대한 예외 처리")
    @Test
    void isHoliday() {
        assertThat(Date.isHoliday(LocalDate.of(2023, 12, 31))).isEqualTo(false);
    }

    @DisplayName("두 날짜 사이의 기간이 일치하는지 검사")
    @Test
    void period() {
        assertThat(Date.period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31))).isEqualTo(30);
        assertThat(Date.period(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 12, 31))).isEqualTo(60);
    }

}