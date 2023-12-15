package christmas.validator;

public class InputValidator extends Validator {

    @Override
    public void validate(String input)  {
        validateEnglish(input);
        // 검증 로직 작성
    }
}