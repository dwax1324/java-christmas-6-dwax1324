package christmas;

import christmas.controller.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventService.of(new InputView(), new OutputView()).run();
    }
}