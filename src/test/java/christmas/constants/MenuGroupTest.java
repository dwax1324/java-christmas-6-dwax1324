package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGroupTest {

    @Test
    void findCategoryByName() {
        System.out.println(MenuGroup.findCategoryByName("없음"));
    }


    @DisplayName("카테고리에 메뉴가 속해있는지 확인")
    @Test
    void hasName() {
        assertThat(MenuGroup.APPETIZER.hasName("양송이수프")).isEqualTo(true);
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}