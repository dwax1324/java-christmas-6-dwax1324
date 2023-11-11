package christmas.constants.messages;

public enum Notification {
    DESIGNATED_DAY_DISCOUNT("크리스마스 디데이 할인"),
    HOLIDAY_DISCOUNT("주말 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    SPECIAL_DAY_DISCOUNT("특별 할인"),
    GIFT_EVENT("증정 이벤트"),
    ;


    private final String message;

    Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}