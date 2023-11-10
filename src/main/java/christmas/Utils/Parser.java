package christmas.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static List<String> parseMenuInput(String input) {
        List<String> parsedInput = Arrays.stream(input.split(",")).toList();
        List<String> result = new ArrayList<>();
        for (String name : parsedInput) {
            List<String> nameAndQuantity = Arrays.stream(name.split("-")).toList();
            validate(nameAndQuantity);
            addNameToResultByQuantity(result, nameAndQuantity);
        }
        return result;
    }

    private static void validate(List<String> nameAndQuantity) {
        isNullOrEmpty(nameAndQuantity);
        isParsedToNameAndQuantity(nameAndQuantity);
        isQuantityParsible(nameAndQuantity);
        Integer quantity = Integer.parseInt(nameAndQuantity.get(1));
        isQuantityInRange(quantity);
    }

    private static void isNullOrEmpty(List<String> nameAndQuantity) {
        if (nameAndQuantity == null || nameAndQuantity.isEmpty()) {
            throw new IllegalArgumentException("input format error");
        }
    }

    private static void isParsedToNameAndQuantity(List<String> nameAndQuantity) {
        if (nameAndQuantity.size() != 2) {
            throw new IllegalArgumentException("FORMAT ERROR");
        }
    }

    private static void isQuantityParsible(List<String> nameAndQuantity) {
        try {
            String quantity = nameAndQuantity.get(1);
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("QUANTITY ERROR");
        }
    }

    private static void isQuantityInRange(Integer quantity) {
        if (quantity > 20) {
            throw new IllegalArgumentException("QUANTITY TOO LARGE");
        }
        if (quantity == 0) {
            throw new IllegalArgumentException("QUANTITY AT LEAST ONE");
        }
    }

    private static void addNameToResultByQuantity(List<String> result, List<String> nameAndQuantity) {
        String name = nameAndQuantity.get(0);
        int quantity = Integer.parseInt(nameAndQuantity.get(1));
        for (int i = 0; i < quantity; i++) {
            result.add(name.trim());
        }
    }
}