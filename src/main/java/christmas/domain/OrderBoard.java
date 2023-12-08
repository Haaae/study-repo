package christmas.domain;

import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import christmas.view.dto.OrderDto;
import java.util.List;

public class OrderBoard {

    private final List<Order> orders;

    public OrderBoard(List<OrderDto> orderDtos, ExceptionCode e) {
        List<Order> orders = orderDtos.stream()
                .map(dto -> new Order(dto.menu(), dto.count(), e))
                .toList();

        validateDuplication(e, orders);
        validateCount(e, orders);
        validateMenuType(e, orders);

        this.orders = orders;
    }

    private static void validateMenuType(ExceptionCode e, List<Order> orders) {
        Validator.isNotSame(
                orders.size(),
                orders.stream()
                        .filter(order -> order.isSameMenuType(MenuType.DRINK))
                        .count(),
                e
        );
    }

    private static void validateCount(ExceptionCode e, List<Order> orders) {
        Validator.isValidRange(
                orders.stream()
                        .mapToInt(Order::getCount)
                        .sum(),
                Menu.MIN_COUNT,
                Menu.MAX_COUNT,
                e
        );
    }

    private static void validateDuplication(ExceptionCode e, List<Order> orders) {
        Validator.isDuplication(orders, e);
    }
}
