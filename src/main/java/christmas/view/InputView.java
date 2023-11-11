package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public Integer getDate() {
        String input = Console.readLine();
        validateNullOrEmpty(input);
        validateParsible(input);
        return Integer.parseInt(input);
    }

    public String getMenu() {
        String input = Console.readLine();
        validateNullOrEmpty(input);
        validateFormatOfMenu(input);
        return input;
    }

    private void validateFormatOfMenu(String input) {
        List<String> menus = Arrays.stream(input.split(",")).toList();
        for (String menu : menus) {
            validateNullOrEmpty(menu);
            validateDelimiterOfMenu(menu);
        }
    }

    private void validateDelimiterOfMenu(String menu) {
        menu = menu.trim();
        int numberOfDash = menu.length() - menu.replace("-", "").length();
        int index = menu.indexOf('-');
        if (index == menu.length() - 1 || index == 0 || numberOfDash != 1) {
            throw new IllegalArgumentException("입력 형식에 맞춰서 입력해주세요");
        }
    }


    private void validateParsible(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당월에 해당하는 날짜만 입력해 주세요");
        }
    }


    private void validateNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력을 해주세요");
        }
    }
}
