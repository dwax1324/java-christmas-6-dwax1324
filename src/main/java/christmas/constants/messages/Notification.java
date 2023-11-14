package christmas.constants.messages;

public enum Notification {
    DESIGNATED_DAY_DISCOUNT("크리스마스 디데이 할인"),
    HOLIDAY_DISCOUNT("주말 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    SPECIAL_DAY_DISCOUNT("특별 할인"),
    GIFT_EVENT("증정 이벤트"),
    SANTA("산타"),
    TREE("트리"),
    STAR("별"),
    NONE("없음"),
    CHAMPAGNE("샴페인"),
    GREETING("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    ASK_DATE("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    RESULT("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    private final String message;

    Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}