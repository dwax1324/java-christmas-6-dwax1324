package christmas.domain.menu;

import christmas.constants.MenuGroup;
import christmas.constants.messages.Error;

public class Menu {
    private final String name;
    private final String category;
    private final Integer price;

    Menu(String name) {
        validate(name);
        this.name = name;
        this.category = MenuGroup.findCategoryByName(name);
        this.price = MenuGroup.findPriceByName(name);
    }

    public static Menu of(String name) {
        return new Menu(name);
    }

    private void validate(String name) {
        if (!MenuGroup.isNameExist(name)) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCategoryByCategoryName(String categoryName) {
        return this.category.equals(categoryName);
    }

    public boolean equals(Menu menu) {
        return this.name.equals(menu.name);
    }
}