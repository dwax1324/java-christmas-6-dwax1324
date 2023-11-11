package christmas.view;

import christmas.constants.Policy;
import christmas.dto.EventPlannerDto;
import christmas.dto.MenusDto;
import java.util.List;
import java.util.Map.Entry;

public class OutputView {

    public void greeting() {
        System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", Policy.MONTH.getValue());
        System.out.println();
    }

    public void askDate() {
        System.out.printf("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)", Policy.MONTH.getValue());
        System.out.println();
    }

    public void askMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printResult(EventPlannerDto eventPlannerDto) {
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", Policy.MONTH.getValue(), eventPlannerDto.date());
        System.out.println();
        System.out.println();
        printOrderedMenu(eventPlannerDto.menusDto());
        printBeforeDiscount(eventPlannerDto.beforeDiscount());
        printGift(eventPlannerDto.isGift());
        printDiscounts(eventPlannerDto.discounts());
        printTotalBenefit(eventPlannerDto.totalBenefit());
        printAfterDiscount(eventPlannerDto.afterDiscount());
        printBadge(eventPlannerDto.badge());
    }

    private void printTotalBenefit(Integer benefit) {
        System.out.println("<총혜택 금액>");
        if (benefit != 0) {
            System.out.print("-");
        }
        System.out.println(String.format("%,d", benefit) + "원");
        System.out.println();
    }

    private void printBadge(String badge) {
        System.out.printf("<%d월 이벤트 배지>\n", Policy.MONTH.getValue());
        System.out.println(badge);
    }

    private void printAfterDiscount(Integer price) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d", price) + "원");
        System.out.println();
    }

    private void printDiscounts(List<Entry<String, Integer>> discounts) {
        System.out.println("<혜택 내역>");
        discounts.forEach(r -> System.out.println(r.getKey() + ": -" + String.format("%,d", r.getValue())));
        System.out.println();
    }

    private void printGift(Boolean gift) {
        System.out.println("<증정 메뉴>");
        if (gift) {
            System.out.println("샴페인 1개");
            System.out.println();
            return;
        }
        System.out.println("없음");
        System.out.println();
    }


    private void printBeforeDiscount(Integer price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", price) + "원");
        System.out.println();
    }

    private void printOrderedMenu(MenusDto menusDto) {
        System.out.println("<주문 메뉴>");
        menusDto.menus().forEach(r -> System.out.println(r.getKey() + " " + r.getValue() + "개"));
        System.out.println();
    }
}
