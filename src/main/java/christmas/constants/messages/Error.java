package christmas.constants.messages;

public enum Error {
    DATE("날짜"),
    MENU("주문");
    private final String error;

    Error(String error) {
        this.error = error;
    }

    public String getMessage() {
        return String.format("[ERROR] 유효하지 않은 %s입니다. 다시 입력해 주세요.", this.error);
    }
}
