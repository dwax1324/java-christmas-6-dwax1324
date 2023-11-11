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

    private void validate(String name) {
        if (!MenuGroup.isNameExist(name)) {
            throw new IllegalArgumentException();
        }
    }

    public Integer getPrice() {
        return this.price;
    }

    public boolean isCategoryByCategoryName(String categoryName) {
        return this.category.equals(categoryName);
    }

    public static Menu of(String name) {
        return new Menu(name);
    }

    public boolean equals(Menu menu) {
        return this.name.equals(menu.name);
    }
}
