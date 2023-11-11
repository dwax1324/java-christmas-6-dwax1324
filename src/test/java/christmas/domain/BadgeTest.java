package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("of test")
    @Test
    void of() {
        assertThatCode(() -> Badge.of(10000)).doesNotThrowAnyException();
    }

    @DisplayName("name test")
    @Test
    void go() {
        assertThat(Badge.of(4000).getName()).isEqualTo("없음");
        assertThat(Badge.of(5000).getName()).isEqualTo("별");
        assertThat(Badge.of(10000).getName()).isEqualTo("트리");
        assertThat(Badge.of(20000).getName()).isEqualTo("산타");
    }
}