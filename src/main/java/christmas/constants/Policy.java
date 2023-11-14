package christmas.constants;


public enum Policy {
    YEAR(2023),
    MONTH(12),
    CHRISTMAS_DAY(25),
    MIN_COST_FOR_PROMOTION(10_000),
    MAX_MENU_SIZE(20),
    MIN_COST_FOR_GIFT(120_000),
    MIN_COST_FOR_DISCOUNT(10_000),
    HOLIDAY_DISCOUNT(2023),
    WEEKDAY_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1_000),
    DDAY_INITIAL_DISCOUNT(1_000),
    DDAY_AFTER_DISCOUNT(100),
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000),
    CHAMPAGNE(25_000),
    GIFT_QUANTITY(1);

    private final Integer value;

    Policy(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}