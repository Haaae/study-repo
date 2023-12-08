package christmas.domain.event;

import christmas.domain.menu.MenuType;
import christmas.domain.order.OrderBoard;
import christmas.domain.date.ReservationDate;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Event {
    CHRISTMAS(
            "크리스마스 디데이 할인",
            (d, o) -> d.isNotOverChristmas() && o.hasBiggerOrSameTotalPriceThan(10_000),
            (d, o) -> d.getDate() * 100 + 900,
            false,
            "없음"
    ),
    WEEKDAY(
            "평일 할인",
                    (d, o) ->
                            d.isWeekday()
                            && o.hasBiggerOrSameTotalPriceThan(10_000)
                            && o.getCountOfMenuType(MenuType.DESSERT) != 0,
            (d, o) -> o.getCountOfMenuType(MenuType.DESSERT) * 2023,
            false,
            "없음"
    ),
    WEEKEND(
            "주말 할인",
                    (d, o) ->
                            d.isWeekend()
                            && o.hasBiggerOrSameTotalPriceThan(10_000)
                            && o.getCountOfMenuType(MenuType.MAIN) != 0,
            (d, o) -> o.getCountOfMenuType(MenuType.MAIN) * 2023,
            false,
            "없음"
    ),
    SPEICAL(
            "특별 할인",
                    (d, o) ->
                            (d.isSunday() || d.isChristmas())
                            && o.hasBiggerOrSameTotalPriceThan(10_000),
            (d, o) -> 1_000,
            false,
            "없음"
    ),
    GIFT(
            "증정 이벤트",
                    (d, o) -> o.hasBiggerOrSameTotalPriceThan(120_000),
            (d, o) -> 25_000,
            true,
            "샴페인 1개"
    ),
    ;

    private final String name;
    private final BiPredicate<ReservationDate, OrderBoard> predicateApply;
    private final BiFunction<ReservationDate, OrderBoard, Integer> calculateDiscountPrice;
    private final boolean hasGift;
    private final String gift;

    Event(
            String name,
            BiPredicate<ReservationDate, OrderBoard> predicateApply,
            BiFunction<ReservationDate, OrderBoard, Integer> calculateDiscountPrice,
            boolean hasGift,
            String gift
    ) {
        this.name = name;
        this.predicateApply = predicateApply;
        this.calculateDiscountPrice = calculateDiscountPrice;
        this.hasGift = hasGift;
        this.gift = gift;
    }


    public boolean canApply(ReservationDate date, OrderBoard orderBoard) {
        return predicateApply.test(date, orderBoard);
    }

    public int apply(ReservationDate date, OrderBoard orderBoard) {
        return calculateDiscountPrice.apply(date, orderBoard);
    }

    public boolean hasGift() {
        return hasGift;
    }

    public String getGift() {
        return gift;
    }
}
