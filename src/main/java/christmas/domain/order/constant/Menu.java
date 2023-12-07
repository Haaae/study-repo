package christmas.domain.order.constant;

import christmas.exception.ExceptionCode;
import java.util.Arrays;

public enum Menu {

    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6_000),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000),

    STEAK(MenuType.MAIN, "티본스테이크", 55_000),
    BBQ(MenuType.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5_000),

    COKE(MenuType.DRINK, "제로콜라", 3_000),
    RED_WINE(MenuType.DRINK, "레드와인", 60_000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25_000),
    ;

    private final MenuType type;
    private final String name;
    private final int price;

    Menu(final MenuType type, final String name, final int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public static Menu from(final String menu, final ExceptionCode e) {
        return Arrays.stream(values())
                .filter(m -> m.name.equals(menu))
                .findFirst()
                .orElseThrow(
                        e.getConstructorWithMessage()
                );
    }


    public boolean is(MenuType type) {
        return this.type == type;
    }

    public int getPrice() {
        return price;
    }

}
