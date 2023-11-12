package christmas;

import christmas.controller.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventService eventService = new EventService(new InputView(), new OutputView());
        eventService.run();
    }
}