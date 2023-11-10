package christmas.constants;


import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public enum MenuGroup {

    APPETIZER(Map.of("양송이수프", 6000, "타파스", 5000, "시저샐러드", 8000)),
    MAIN(Map.of("티본스테이크", 55000, "바비큐립", 54000, "해산물파스타", 35000, "크리스마스파스타", 25000)),
    DESSERT(Map.of("초코케이크", 15000, "아이스크림", 5000)),
    BEVERAGE(Map.of("제로콜라", 3000, "레드와인", 60000, "샴페인", 25000)),
    NONE(Collections.EMPTY_MAP);
    private final Map<String, Integer> menuInformation;

    MenuGroup(Map<String, Integer> menuInformation) {
        this.menuInformation = menuInformation;
    }

    public boolean hasName(String name) {
        return menuInformation.containsKey(name);
    }

    public Integer getPriceByName(String name) {
        return menuInformation.get(name);
    }

    public static String findCategoryByName(String name) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasName(name))
                .findAny().orElse(NONE)
                .name();
    }

    public static Integer findPriceByName(String name) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasName(name))
                .findAny().orElse(NONE)
                .getPriceByName(name);

    }

    public static boolean isNameExist(String name) {
        return !findCategoryByName(name).equals("NONE");
    }
}