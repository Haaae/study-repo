package christmas.service.dto;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.OrderBoard;
import java.util.List;
import java.util.Map;

public record EventPreviewDto(
        Map<String, Integer> menuWithCount,
        int totalPurchasePrice,
        Map<Event, Integer> eventWithDiscountPrice,
        Badge badge
) {
    public EventPreviewDto(
            OrderBoard orderBoard,
            Map<Event, Integer> eventWithDiscountPrice,
            Badge badge
    ) {
        this(
                orderBoard.getMenuWithCount(),
                orderBoard.getTotalPurchasePrice(),
                eventWithDiscountPrice,
                badge
        );
    }

    public int totalDiscountPrice() {
        return eventWithDiscountPrice.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int totalPriceAppliedEvent() {
        return totalPurchasePrice - totalDiscountPrice();
    }

    public List<String> gifts() {
        return eventWithDiscountPrice.keySet()
                .stream()
                .filter(Event::hasGift)
                .map(Event::getGift)
                .toList();
    }
}
