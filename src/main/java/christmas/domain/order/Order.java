package christmas.domain.order;

import christmas.domain.order.constant.Menu;
import christmas.domain.order.constant.MenuCount;
import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import java.util.Objects;

public class Order {

    private final Menu menu;
    private final int count;

    public Order(final String menu, final int count) {
        ExceptionCode e = ExceptionCode.INVALID_ORDER;

        Validator.isValidRange(
                count,
                MenuCount.MINIMUM.getValue(),
                MenuCount.MAXIMUM.getValue(),
                e);

        this.menu = Menu.from(menu, e);
        this.count = count;
    }

    public boolean isDrink() {
        return Menu.isDrink(menu);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
