package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import christmas.constants.MenuGroup;
import christmas.domain.startegies.DesignatedDayStrategy;
import christmas.domain.startegies.HolidayStrategy;
import christmas.domain.startegies.SpecialStrategy;
import christmas.domain.startegies.WeekDayStrategy;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    Menus menus = Menus.from("티본스테이크-1");

    @DisplayName("Discount 객체 생성 테스트")
    @Test
    void createDiscountTest() {
        assertThatCode(() -> Discount.of(3, menus)).doesNotThrowAnyException();
    }

    @DisplayName("Discount 객체 생성 테스트")
    @Test
    void Given_Menus_When_DiscountAppliedDiscountStrategies_Then_ResultIsExpected() {
        // given
        Discount discount = Discount.of(3, Menus.from("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"));
        // when
        int dDayDiscount = discount.calculateByStrategies(new DesignatedDayStrategy()).stream()
                .mapToInt(Entry::getValue).sum();
        int holidayDiscount = discount.calculateByStrategies(new HolidayStrategy()).stream()
                .mapToInt(Entry::getValue).sum();
        int weekDayDiscount = discount.calculateByStrategies(new WeekDayStrategy()).stream()
                .mapToInt(Entry::getValue).sum();
        int specialDiscount = discount.calculateByStrategies(new SpecialStrategy()).stream()
                .mapToInt(Entry::getValue).sum();
        // then
        assertThat(dDayDiscount).isEqualTo(1200);
        assertThat(weekDayDiscount).isEqualTo(4046);
        assertThat(holidayDiscount).isEqualTo(0);
        assertThat(specialDiscount).isEqualTo(1000);
    }

    @DisplayName("Discount 날짜 반환 메서드 테스트")
    @Test
    void getDateTest() {
        int date = 3;
        assertThat(Discount.of(date, menus).getDate()).isEqualTo(date);
    }

    @DisplayName("Discount 카테고리 개수 반환 메서드 테스트")
    @Test
    void countCategoryTest() {
        Menus menus = Menus.from("티본스테이크-10");
        assertThat(Discount.of(1, menus).countCategory(MenuGroup.MAIN.name())).isEqualTo(10);
    }

    @DisplayName("Discount 일요일인지 확인하는 반환 메서드 테스트")
    @Test
    void isSundayTest() {
        assertThat(Discount.of(3, menus).isSunday()).isEqualTo(true);
    }


    @DisplayName("Discount 주말인지 확인하는 반환 메서드 테스트")
    @Test
    void isHolidayTest() {
        assertThat(Discount.of(1, menus).isHoliday()).isEqualTo(true);
        assertThat(Discount.of(2, menus).isHoliday()).isEqualTo(true);
    }

    @DisplayName("Discount 크리스마스 날인지 확인하는 반환 메서드 테스트")
    @Test
    void isChristmasDayTest() {
        assertThat(Discount.of(25, menus).isChristmasDay()).isEqualTo(true);
        assertThat(Discount.of(26, menus).isChristmasDay()).isEqualTo(false);
    }
}