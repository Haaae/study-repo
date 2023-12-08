package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import christmas.view.dto.OrderDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public boolean hasBiggerOrSameTotalPriceThan(int price) {
        return getTotalPurchasePrice() >= price;
    }

    public int getTotalPurchasePrice() {
        return orders.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public Map<String, Integer> getMenuWithCount() {
        return orders.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Order::getName,
                        Order::getCount
                ));
    }

    public int getCountOfMenuType(MenuType type) {
        return (int) orders.stream()
                .filter(order -> order.isSameMenuType(type))
                .count();
    }
}
