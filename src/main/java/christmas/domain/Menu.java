package christmas.domain;

import christmas.constants.MenuGroup;

public class Menu {
    private String name;
    private String category;
    private Integer price;

    Menu(String name) {
        validate(name);
        this.name = name;
        this.category = MenuGroup.findCategoryByName(name);
        this.price = MenuGroup.findPriceByName(name);
    }

    void validate(String name) {
        if (MenuGroup.findCategoryByName(name).equals("NONE")) {
            throw new IllegalArgumentException();
        }
    }

    public static Menu of(String name) {
        return new Menu(name);
    }
}
