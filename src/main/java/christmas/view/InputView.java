package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.messages.Error;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public Integer getDate() {
        String input = Console.readLine();
        validateNullOrEmpty(input, Error.DATE.getMessage());
        validateParsible(input, Error.DATE.getMessage());
        return Integer.parseInt(input);
    }

    public String getMenu() {
        String input = Console.readLine();
        validateNullOrEmpty(input, Error.MENU.getMessage());
        validateFormatOfMenu(input);
        return input;
    }

    private void validateFormatOfMenu(String input) {
        List<String> menus = Arrays.stream(input.split(",")).toList();
        if (menus.isEmpty()) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
        for (String menu : menus) {
            validateNullOrEmpty(menu, Error.MENU.getMessage());
            validateDelimiterOfMenu(menu);
        }
    }

    private void validateDelimiterOfMenu(String menu) {
        menu = menu.trim();
        int numberOfDash = menu.length() - menu.replace("-", "").length();
        int index = menu.indexOf('-');
        if (index == menu.length() - 1 || index == 0 || numberOfDash != 1) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }


    private void validateParsible(String input, String errorMessage) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }


    private void validateNullOrEmpty(String input, String errorMessage) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
