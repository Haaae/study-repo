package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import java.util.Objects;

public class Order {

    private final Menu menu;
    private final int count;

    public Order(String menu, int count, ExceptionCode e) {
        Validator.isValidRange(count, Menu.MIN_COUNT, Menu.MAX_COUNT, e);

        this.menu = Menu.from(menu, e);
        this.count = count;
    }

    public boolean isSameMenuType(MenuType type) {
        return menu.isSameMenuType(type);
    }

    public int getPrice() {
        return count * menu.getPrice();
    }


    public int getCount() {
        return count;
    }

    public String getName() {
        return menu.getName();
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
