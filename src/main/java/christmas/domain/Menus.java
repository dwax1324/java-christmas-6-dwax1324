package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Menus {
    private final List<Menu> menus;
    private Menus(String menus){
        validateMenus(menus);
//        System.out.print(Arrays.stream(menus.split(",")).map((r)->Menu.of(r).name).collect(Collectors.toList()));
        this.menus = Arrays.stream(menus.split(",")).map(Menu::of).collect(Collectors.toList());
    }


    public static Menus from(String menus){
        return new Menus(menus);
    }
    private void validateMenus(String s) {
    }

}
