package christmas.utils;

import christmas.constants.Policy;
import christmas.constants.messages.Error;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    public static List<String> parseMenuInput(String input) {
        List<String> parsedInput = Arrays.stream(input.split(",")).toList();
        if (parsedInput.isEmpty()) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
        List<String> result = new ArrayList<>();
        Set<String> names = new HashSet<>();
        parse(parsedInput, result, names);
        return result;
    }

    private static void parse(List<String> parsedInput, List<String> result, Set<String> names) {
        for (String name : parsedInput) {
            List<String> nameAndQuantity = Arrays.stream(name.split("-")).toList();
            nameAndQuantity = nameAndQuantity.stream().map(String::trim).toList();
            validate(nameAndQuantity);
            if (!names.add(nameAndQuantity.get(0))) {
                throw new IllegalArgumentException(Error.MENU.getMessage());
            }
            addNameToResultByQuantity(result, nameAndQuantity);
        }
    }

    private static void validate(List<String> nameAndQuantity) {
        isNullOrEmpty(nameAndQuantity);
        isParsedToNameAndQuantity(nameAndQuantity);
        isQuantityParsible(nameAndQuantity);
        isQuantityInRange(nameAndQuantity);
    }

    private static void isNullOrEmpty(List<String> nameAndQuantity) {
        if (nameAndQuantity == null || nameAndQuantity.isEmpty()) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }

    private static void isParsedToNameAndQuantity(List<String> nameAndQuantity) {
        if (nameAndQuantity.size() != 2) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }

    private static void isQuantityParsible(List<String> nameAndQuantity) {
        if (nameAndQuantity.get(1).charAt(0) == '0') { // quantity can not begin with zero
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
        try {
            Integer.parseInt(nameAndQuantity.get(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
        }
    }

    private static void isQuantityInRange(List<String> nameAndQuantity) {
        int quantity = Integer.parseInt(nameAndQuantity.get(1));
        if (quantity > Policy.MAX_MENU_SIZE.getValue()) {
            throw new IllegalArgumentException(Error.MENU.getMessage());
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