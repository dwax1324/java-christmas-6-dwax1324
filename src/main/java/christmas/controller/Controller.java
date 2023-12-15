package christmas.controller;

import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try{
            firstCommand();
        }catch(IllegalArgumentException e){
            outputView.printByMessage(e.getMessage());
        }
    }

    private void firstCommand() {

    }
}