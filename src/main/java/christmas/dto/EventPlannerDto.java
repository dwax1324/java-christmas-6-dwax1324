package christmas.dto;

import java.util.List;
import java.util.Map.Entry;

public record EventPlannerDto(Integer date, MenusDto menusDto, Integer beforeDiscount, Boolean isGift,
                              List<Entry<String, Integer>> discounts, Integer totalBenefit, Integer afterDiscount,
                              String badge) {
}
