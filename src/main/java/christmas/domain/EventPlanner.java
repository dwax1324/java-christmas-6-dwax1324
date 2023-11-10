package christmas.domain;

public class EventPlanner {

    final Integer date;
    final Menus menus;
    private EventPlanner(Integer date, String menus){
        validate(date);
        this.date = date;
        this.menus = Menus.from(menus);
    }

    private void validate(Integer date){
        if(date < 1 || date > 31) throw new IllegalArgumentException("[ERROR]: 유효한 날짜를 입력해주세요");
    }

    public static EventPlanner of(Integer date, String menus){
        return new EventPlanner(date,menus);
    }
}
