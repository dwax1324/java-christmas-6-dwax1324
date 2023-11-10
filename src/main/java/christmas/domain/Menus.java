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
}
