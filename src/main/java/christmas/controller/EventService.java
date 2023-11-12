package christmas.controller;

import christmas.domain.EventPlanner;
import christmas.domain.Menus;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventService {

    private final OutputView outputView;
    private final InputView inputView;

    public EventService(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.greeting();
        outputView.askDate();
        Integer date = getDateUntilNoException();
        outputView.askMenu();
        Menus menus = getMenusUntilNoException();
        EventPlanner eventPlanner = EventPlanner.of(date, menus);
        outputView.printResult(eventPlanner.toDto());
    }

    private Menus getMenusUntilNoException() {
        while (true) {
            try {
                Menus menus = Menus.from(inputView.getMenu());
                menus.totalPriceLessThan(10000);
                return menus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Integer getDateUntilNoException() {
        while (true) {
            try {
                Integer date = inputView.getDate();
                EventPlanner.validateDate(date);
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}