package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.ReservationDate;
import christmas.domain.OrderBoard;
import christmas.service.dto.EventPreviewDto;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChristmasService {

    private static final ChristmasService instance = new ChristmasService();

    private ChristmasService() {
    }

    public static ChristmasService getInstance() {
        return instance;
    }

    public EventPreviewDto getPreview(ReservationDate date, OrderBoard orderBoard) {
        Map<Event, Integer> eventWithDiscountPrice = getEventWithDiscountPrice(date, orderBoard);

        int totalDiscountPrice = getTotalDiscountPrice(eventWithDiscountPrice);

        Badge badge = Badge.from(totalDiscountPrice);

        return new EventPreviewDto(orderBoard, eventWithDiscountPrice, badge);
    }

    private Map<Event, Integer> getEventWithDiscountPrice(ReservationDate date, OrderBoard orderBoard) {
        return Stream.of(Event.values())
                .filter(event -> event.canApply(date, orderBoard))
                .collect(Collectors.toUnmodifiableMap(
                        event -> event,
                        event -> event.apply(date, orderBoard)
                ));
    }

    private int getTotalDiscountPrice(Map<Event, Integer> eventWithDiscountPrice) {
        return eventWithDiscountPrice.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
