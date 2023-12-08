package christmas.domain.menu;

import christmas.exception.ExceptionCode;
import java.util.stream.Stream;

public enum Menu {

    MUSHROOM_SOUP(MenuType.APPETIZER, 6_000, "양송이수프"),
    TAPAS(MenuType.APPETIZER, 5_500, "타파스"),
    SALAD(MenuType.APPETIZER, 8_000, "시저샐러드"),

    STEAK(MenuType.MAIN, 55_000, "티본스테이크"),
    BBQ_RIBS(MenuType.MAIN, 54_000, "바비큐립"),
    SEAFOOD_PASTA(MenuType.MAIN, 35_000, "해산물파스타"),
    CHRISTMAS_PASTA(MenuType.MAIN, 25_000, "크리스마스파스타"),

    CAKE(MenuType.DESSERT, 15_000, "초코케이크"),
    ICE_CREAM(MenuType.DESSERT, 5_000, "아이스크림"),

    COKE(MenuType.DRINK, 3_000, "제로콜라"),
    RED_WINE(MenuType.DRINK, 60_000, "레드와인"),
    CHAMPAGNE(MenuType.DRINK, 25_000, "샴페인"),
    ;

    public static final int MIN_COUNT = 1;
    public static final int MAX_COUNT = 20;

    private final MenuType type;
    private final int price;
    private final String name;

    Menu(MenuType type, int price, String name) {
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public static Menu from(String menu, ExceptionCode e) {
        return Stream.of(values())
                .filter(m -> m.name.equals(menu))
                .findFirst()
                .orElseThrow(
                        e.getConstructorWithMessage()
                );
    }

    public boolean isSameMenuType(MenuType type) {
        return this.type == type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
