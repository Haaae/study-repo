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

    public Date createDate(final int date, final ExceptionCode e) {
        return new Date(date, e);
    }

    public OrderBoard createOrderBoard(final List<OrderDto> orders, final ExceptionCode e) {
        return new OrderBoard(orders, e);
    }

    public Preview getPreviewOfEvent(final Date reservationDate, final OrderBoard orderBoard) {
        // 예약 생성
        Reservation reservation = new Reservation(reservationDate, orderBoard);

        // 적용 가능 이벤트 적용
        Map<Event, Integer> eventsWithDiscountPrice = new EnumMap<>(Event.class);
        Stream.of(Event.values())
                .filter(event -> event.canApply(reservation))
                .forEach(event ->
                        eventsWithDiscountPrice.put(
                                event, event.apply(reservation)
                        )
                );

        return new Preview(
                reservation,
                eventsWithDiscountPrice
        );
    }
}
