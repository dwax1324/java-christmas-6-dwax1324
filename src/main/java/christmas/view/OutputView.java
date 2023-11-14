package christmas.view;

import christmas.constants.Policy;
import christmas.constants.messages.Notification;
import christmas.dto.EventPlannerDto;
import christmas.dto.MenusDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void greeting() {
        System.out.printf(Notification.GREETING.getMessage(), Policy.MONTH.getValue());
        System.out.println();
    }

    public void askDate() {
        System.out.printf(Notification.ASK_DATE.getMessage(), Policy.MONTH.getValue());
        System.out.println();
    }

    public void askMenu() {
        System.out.println(Notification.ASK_MENU.getMessage());
    }

    public void printResult(EventPlannerDto eventPlannerDto) {
        System.out.printf(Notification.RESULT.getMessage(), Policy.MONTH.getValue(), eventPlannerDto.date());
        System.out.println();
        System.out.println();
        getResultOrder(eventPlannerDto).forEach(result -> print(result.getKey(), result.getValue()));
    }

    private List<Entry<String, List<String>>> getResultOrder(EventPlannerDto eventPlannerDto) {
        // 제목,내용
        return List.of(Map.entry("주문 메뉴", formatMenu(eventPlannerDto.menusDto())),
                Map.entry("할인 전 총주문 금액", formatBeforeDiscount(eventPlannerDto.beforeDiscount())),
                Map.entry("증정 메뉴", formatGift(eventPlannerDto.isGift())),
                Map.entry("혜택 내역", formatDiscounts(eventPlannerDto.discounts())),
                Map.entry("총혜택 금액", formatTotalBenefit(eventPlannerDto.totalBenefit())),
                Map.entry("할인 후 예상 결제 금액", formatAfterDiscount(eventPlannerDto.afterDiscount())),
                Map.entry(String.format("%d월 이벤트 배지", Policy.MONTH.getValue()), formatBadge(eventPlannerDto.badge())));
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
        if (discounts.isEmpty()) {
            return List.of(Notification.NONE.getMessage());
        }
        return discounts.stream().map(r -> r.getKey() + ": -" + String.format("%,d", r.getValue()) + "원").toList();
    }

    private List<String> formatGift(Boolean gift) {
        if (gift) {
            return List.of(String.format(Notification.CHAMPAGNE.getMessage() + " %d개",
                    Policy.GIFT_QUANTITY.getValue()));
        }
        return List.of(Notification.NONE.getMessage());
    }

    private List<String> formatBeforeDiscount(Integer beforeDiscount) {
        return List.of(String.format("%,d", beforeDiscount) + "원");
    }

    private List<String> formatMenu(MenusDto menusDto) {
        return menusDto.menus().stream().map(menu -> menu.getKey() + " " + menu.getValue().toString() + "개").toList();
    }
}
