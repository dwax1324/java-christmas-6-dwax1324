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


    }

    private Menus getMenusUntilNoException() {
        while (true) {
            try {
                return Menus.from(inputView.getMenu());
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