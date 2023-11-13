package christmas.dto;

import java.util.List;
import java.util.Map.Entry;

public record MenusDto(List<Entry<String, Integer>> menus) {
}
