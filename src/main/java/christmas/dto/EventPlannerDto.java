package christmas.dto;

import java.util.List;
import java.util.Map.Entry;

public record EventPlannerDto(MenusDto menusDto, Integer price, Boolean isGift,
                              List<Entry<String, Integer>> discounts,
                              Integer totalDiscount, String badge) {
}
