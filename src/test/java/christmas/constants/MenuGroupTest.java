package christmas.constants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuGroupTest {

    List<String> main = List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타");
    List<Integer> mainPrice = List.of(55000, 54000, 35000, 25000);

    @DisplayName("저장 되지 않은 이름에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"없음", "애송이수프", "티포스", "똥양꿍", ""})
    void findCategoryByName() {
        assertThat(MenuGroup.findCategoryByName("없음")).isEqualTo("NONE");
    }


    @DisplayName("카테고리에 메뉴가 속해있는지 확인")
    @Test
    void hasName() {
        for (String name : main) {
            assertThat(MenuGroup.findCategoryByName(name)).isEqualTo("MAIN");
        }
    }

    @DisplayName("저장된 가격이 일치하는지 확인")
    @Test
    void findPriceByName() {
        for (int i = 0; i < 4; i++) {
            assertThat(MenuGroup.findPriceByName(main.get(i))).isEqualTo(mainPrice.get(i));
        }
    }

    @Test
    void valueOf() {
    }
}