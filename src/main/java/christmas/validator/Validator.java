package christmas.validator;

import bridge.utils.constants.ExceptionMessage;
import java.util.regex.Pattern;
public abstract class Validator {

    private static final Pattern NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    private static final Pattern KOREAN_REGEX = Pattern.compile("^[ㄱ-ㅎ가-힣]*$");
    private static final Pattern ENGLISH_REGEX = Pattern.compile("^[a-zA-Z]*$");

    abstract void validate(String input);

    void validateNumeric(String input) {
        if (!NUMBER_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }
    void validateKorean(String input) {
        if (!KOREAN_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    void validateEnglish(String input) {
        if (!ENGLISH_REGEX.matcher(input).matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage());
        }
    }

    void validateParsible(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getMessage(), exception);
        }
    }

    void validateNumberRange(String input,Integer inclusiveMinRange, Integer inclusiveMaxRange) {
        int number = Integer.parseInt(input);
        if (number < inclusiveMinRange || number > inclusiveMaxRange) {
            throw new IllegalArgumentException();
        }
    }

}