package christmas.domain.order;

import christmas.domain.order.constant.MenuCount;
import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import christmas.view.dto.OrderDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderBoard {

    private final List<Order> orders;

    public OrderBoard(final List<OrderDto> orderDtos, final ExceptionCode e) {
        List<Order> orders = convertToOrders(orderDtos);

        validateDuplication(orders, e);
        validateTotalCount(orders, e);
        validateMenuType(orders, e);

        this.orders = orders;
    }

    private static List<Order> convertToOrders(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(dto -> new Order(
                        dto.menu(), dto.count()
                ))
                .toList();
    }

    private static void validateDuplication(List<Order> orders, ExceptionCode e) {
        Validator.isDuplication(orders, e);
    }

    private static void validateTotalCount(List<Order> orders, ExceptionCode e) {
        Validator.isValidRange(
                orders.stream()
                        .mapToInt(Order::getCount)
                        .sum(),
                MenuCount.MINIMUM.getValue(),
                MenuCount.MAXIMUM.getValue(),
                e
        );
    }

    private static void validateMenuType(List<Order> orders, ExceptionCode e) {
        Validator.isNotValue(
                orders.stream()
                        .filter(Order::isDrink)
                        .count(),
                orders.size(),
                e
        );
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public int getCountOfMainMenu() {
        return (int) orders.stream()
                .filter(Order::isMain)
                .count();
    }

    public int getCountOfDessertMenu() {
        return (int) orders.stream()
                .filter(Order::isDessert)
                .count();
    }

    public Map<String, Integer> getMenunameWithCount() {
        return orders.stream()
                .collect(
                        Collectors.toUnmodifiableMap(
                                Order::getName,
                                Order::getCount
                        )
                );
    }
}
