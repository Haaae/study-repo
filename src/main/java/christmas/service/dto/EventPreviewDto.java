package christmas.service.dto;

import christmas.domain.badge.Badge;
import christmas.domain.date.ReservationDate;
import christmas.domain.event.Event;
import christmas.domain.order.OrderBoard;
import java.util.List;
import java.util.Map;

public record EventPreviewDto(
        int date,
        Map<String, Integer> menuWithCount,
        int totalPurchasePrice,
        Map<Event, Integer> eventWithDiscountPrice,
        Badge badge
) {
    public EventPreviewDto(
            ReservationDate reservationDate,
            OrderBoard orderBoard,
            Map<Event, Integer> eventWithDiscountPrice,
            Badge badge
    ) {
        this(
                reservationDate.getDate(),
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

    public int totalPriceAppliedByEvent() {
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
