package christmas.view;

import christmas.constants.Policy;
import christmas.dto.EventPlannerDto;
import christmas.dto.MenusDto;
import java.util.List;
import java.util.Map;
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
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", Policy.MONTH.getValue(), eventPlannerDto.date());
        System.out.println();
        final List<Entry<String, List<String>>> resultOrder = List.of(
                Map.entry("주문 메뉴", formatMenu(eventPlannerDto.menusDto())),
                Map.entry("할인 전 총주문 금액", formatBeforeDiscount(eventPlannerDto.beforeDiscount())),
                Map.entry("증정 메뉴", formatGift(eventPlannerDto.isGift())),
                Map.entry("혜택 내역", formatDiscounts(eventPlannerDto.discounts())),
                Map.entry("총혜택 금액", formatTotalBenefit(eventPlannerDto.totalBenefit())),
                Map.entry("할인 후 예상 결제 금액", formatAfterDiscount(eventPlannerDto.afterDiscount())),
                Map.entry("12월 이벤트 배지", formatBadge(eventPlannerDto.badge()))
        );
        resultOrder.forEach(r -> print(r.getKey(), r.getValue()));
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void print(String notification, List<String> datas) {
        System.out.println("<" + notification + ">");
        for (String data : datas) {
            System.out.println(data);
        }
        if (!notification.contains("배지")) {
            System.out.println();
        }
    }

    private List<String> formatBadge(String badge) {
        return List.of(badge);
    }

    private List<String> formatAfterDiscount(Integer afterDiscount) {
        return List.of(String.format("%,d", afterDiscount) + "원");
    }

    private List<String> formatTotalBenefit(Integer benefit) {
        String prefix = "";
        if (benefit > 0) {
            prefix = "-";
        }
        return List.of(prefix + String.format("%,d", benefit) + "원");
    }

    private List<String> formatDiscounts(List<Entry<String, Integer>> discounts) {
        if (discounts == null) {
            return List.of("없음");
        }
        return discounts.stream().map(r -> r.getKey() + ": -" + String.format("%,d", r.getValue()) + "원").toList();
    }

    private List<String> formatGift(Boolean gift) {
        if (gift) {
            return List.of("샴페인 1개");
        }
        return List.of("없음");
    }

    private List<String> formatBeforeDiscount(Integer beforeDiscount) {
        return List.of(String.format("%,d", beforeDiscount) + "원");
    }

    private List<String> formatMenu(MenusDto menusDto) {
        return menusDto.menus().stream().map(menu -> menu.getKey() + " " + menu.getValue().toString() + "개")
                .toList();
    }

}
