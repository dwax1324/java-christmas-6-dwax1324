package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public Integer getDate() {
        String input = Console.readLine();
        validate(input);
        return Integer.parseInt(input);
    }

    private void validate(String input) {
        validateNullOrEmpty(input);
        validateParsible(input);
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
