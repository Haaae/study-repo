package christmas.service;

import christmas.domain.Date;
import christmas.domain.order.Order;
import christmas.domain.order.OrderBoard;
import christmas.exception.ExceptionCode;
import christmas.view.dto.OrderDto;
import java.util.List;
import java.util.Map;

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

    public OrderBoard createOrderBoard(List<OrderDto> orders, final ExceptionCode e) {
        return new OrderBoard(orders, e);
    }
}
