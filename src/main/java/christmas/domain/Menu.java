package christmas.domain;

public class Menu {
    String name;
    String category;
    Menu(String name){
        this.name = name;
    }
    public static Menu of(String name){
        return new Menu(name);
    }
}
