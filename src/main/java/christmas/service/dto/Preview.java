package christmas.service.dto;

import christmas.domain.Badge;
import christmas.domain.event.Event;
import christmas.domain.reservation.Reservation;
import java.util.Map;

public record Preview(
    Map<String, Integer> menunameWithCount,
    int totalPurchasePrice,
    Map<Event, Integer> eventsWithDiscountPrice
) {
    public Preview(final Reservation reservation, final Map<Event, Integer> eventsWithDiscountPrice) {
        this(
                reservation.getMenunameWithCount(),
                reservation.getTotalPrice(),
                eventsWithDiscountPrice
        );

    }

    public int eventDiscountPrice(final Event event) {
        return eventsWithDiscountPrice.getOrDefault(event, 0);
    }

    public int totalDiscountPrice() {
        return eventsWithDiscountPrice.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Badge badge() {
        return Badge.from(totalDiscountPrice());
    }

    public int totalPriceAppliedEvent() {
        return totalPurchasePrice - totalDiscountPrice();
    }
}
