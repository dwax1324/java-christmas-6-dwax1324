package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Policy;

public class InputView {
    public Integer getDate() {
        System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", Policy.MONTH.getValue());
        System.out.println();
        System.out.printf("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)", Policy.MONTH.getValue());
        System.out.println();
        String input = Console.readLine();
        validate(input);
        return null;
    }

    private void validate(String input) {
        validateNullOrEmpty(input);
//        validateNumberSize(input);
    }

    private void validateNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력을 해주세요");
        }
    }

//    private void validateNumberSize(String input) {
//        if(input.length() > 2){
//            throw new IllegalArgumentException("31이하의 수만 적어주세요")
//        }
//    }
//    private void validate(String input) {
//        if(input.length() > 2){
//            throw new IllegalArgumentException("31이하의 수만 적어주세요")
//        }
//    }

}
