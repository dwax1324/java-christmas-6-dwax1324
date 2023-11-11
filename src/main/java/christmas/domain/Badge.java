package christmas.domain;

public class Badge {

    // 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
    private final Integer totalDiscountMoney;

    private Badge(Integer price) {
        this.totalDiscountMoney = price;
    }

    public static Badge of(Integer price) {
        return new Badge(price);
    }

    public String getName() {
        return go(totalDiscountMoney);
    }

    String go(Integer price) {
        if (price >= 20000) {
            return "산타";
        }
        if (price >= 10000) {
            return "트리";
        }
        if (price >= 5000) {
            return "별";
        }
        return "없음";
    }
}
