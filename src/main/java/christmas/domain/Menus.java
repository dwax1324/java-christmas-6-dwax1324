package christmas.domain;

import christmas.constants.MenuGroup;
import christmas.constants.Policy;
import christmas.constants.messages.Error;
import christmas.dto.MenusDto;
import christmas.utils.Parser;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Menus {
    private final List<Menu> menus;

    private Menus(String menus) {
        List<String> parsed = Parser.parseMenuInput(menus);
        validate(parsed);
        this.menus = parsed.stream().map(Menu::of).toList();
    }

    public static Menus from(String menus) {
        return new Menus(menus);
    }

    private void validate(List<String> parsed) {
        validateSize(parsed);
        validateNotOnlyBeverage(parsed);
    }

    private void validateNotOnlyBeverage(List<String> parsed) {
        Set<String> categories = new HashSet<>();
        parsed.forEach(r -> categories.add(MenuGroup.findCategoryByName(r)));
        if (categories.size() == 1 && categories.contains(MenuGroup.BEVERAGE.name())) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }

    private void validateSize(List<String> parsed) {
        if (parsed.size() > Policy.MAX_MENU_SIZE.getValue()) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
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


    public Integer count(Menu menuToCount) {
        return Math.toIntExact(menus.stream().filter(menu -> menu.equals(menuToCount)).count());
    }

    public MenusDto toDto() {
        Map<String, Integer> entities = new HashMap<>();
        menus.forEach((menu) -> entities.put(menu.getName(), this.count(menu)));
        return new MenusDto(entities.entrySet().stream().toList());
    }
}