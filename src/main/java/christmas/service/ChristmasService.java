package christmas.service;

import christmas.domain.date.Date;
import christmas.domain.event.Event;
import christmas.domain.order.OrderBoard;
import christmas.domain.reservation.Reservation;
import christmas.exception.ExceptionCode;
import christmas.service.dto.Preview;
import christmas.view.dto.OrderDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ChristmasService {

    private static final ChristmasService instance = new ChristmasService();

    private ChristmasService() {
    }

    public static ChristmasService getInstance() {
        return instance;
    }

    public Preview getPreviewOfEvent(final Date reservationDate, final OrderBoard orderBoard) {
        Reservation reservation = new Reservation(reservationDate, orderBoard);
        return new Preview(
                reservation,
                getEventsWithDiscountPrice(reservation)
        );
    }

    private Map<Event, Integer> getEventsWithDiscountPrice(Reservation reservation) {
        Map<Event, Integer> eventsWithDiscountPrice = new EnumMap<>(Event.class);
        Stream.of(Event.values())
                .filter(event -> event.canApply(reservation))
                .forEach(event ->
                        eventsWithDiscountPrice.put(
                                event, event.apply(reservation)
                        )
                );
        return eventsWithDiscountPrice;
    }
}
