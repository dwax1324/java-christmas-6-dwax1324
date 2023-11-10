package christmas.constants;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class MenuGroup {
    APPETIZER(List.of("양송이수프,6000", "타파스,5000","시저샐러드,8000")),

    MAIN(List.of("티본스테이크,55000", "바비큐,54000","해산물파스타,35000","크리스마파스타,25000")),

    DESSERT(List.of("초코케이크,15000", "아이스크림,5000")),

    BEVERAGE(List.of("제로콜라,3000", "레드와인,60000","샴페인,25000")),

    NOT_FOUND(Collections.EMPTY_LIST);

    private final List<String> menuInformation;

    MenuGroup(List<String> menuInformation) {
        this.menuInformation = menuInformation;
    }


    public static String findCategoryByName(String name) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasName(name))
                .findAny().orElse(NOT_FOUND).name();
    }

//    public static MenuGroup findPriceByName(String name) {
//        MenuGroup menu = Arrays.stream(MenuGroup.values())
//                .filter(menuGroup -> menuGroup.hasName(name))
//                .findAny().orElse(NOT_FOUND);
//        for (String s : menu.menuInformation) {
//            if (s.contains(name))
//        }
//        return -1;
//    }

    //
    public boolean hasName(String name) {
//        for (String m : menuInformation) {
//            System.err.println(m);
//        }
        return menuInformation.stream().anyMatch(
                menu -> menu.contains(name)
        );
    }
}