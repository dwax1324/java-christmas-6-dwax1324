package christmas.constants;


public enum Policy {

    YEAR(2023),
    MONTH(12),
    CHRISTMAS_DAY(25),
    MIN_COST_FOR_PROMOTION(10_000),
    MAX_MENU(20),
    MIN_COST_BEFORE_PURCHASE_FOR_GIFT(120_000),
    HOLIDAY_DISCOUNT(2023),
    WEEKDAY_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    DDAY_INITIAL_DISCOUNT(1000),
    DDAY_AFTER_DISCOUNT(100),
    STAR(5_000),
    TREE(10_000),
    SANTA(20_200);;

    private final Integer value;

    Policy(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}