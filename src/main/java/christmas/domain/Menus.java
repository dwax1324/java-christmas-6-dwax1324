package christmas.domain;

import christmas.Utils.Parser;
import java.util.List;

public class Menus {
    private final List<Menu> menus;

    private Menus(String menus) {
        List<String> parsed = Parser.parseMenuInput(menus);
        validateSize(parsed);
        this.menus = parsed.stream().map(Menu::of).toList();
    }


    public static Menus from(String menus) {
        return new Menus(menus);
    }

    private void validateSize(List<String> parsed) {
        if (parsed.size() > 20) {
            throw new IllegalArgumentException("MENU TOO MANY");
        }
    }


    public Integer totalPrice() {
        int total = 0;
        for (Menu menu : menus) {
            total += menu.getPrice();
        }
        return total;
    }

    public Integer countCategoryByCategoryName(String categoryName) {
        return this.menus.stream().filter((menu) -> menu.isCategoryByCategoryName(categoryName)).toList().size();
    }


    public Integer count(Menu menu) {
        return Math.toIntExact(menus.stream().filter(m -> m.equals(menu)).count());
    }

//    public MenusDto toDto() {
//        Map<String, Integer> entities = new HashMap<>();

//        return null;
//    }
}
