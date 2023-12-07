package christmas.domain.event;

import christmas.domain.reservation.Reservation;
import java.util.function.Function;

public enum Event {
    CHRISTMAS(
            reservation ->
                    reservation.isNotOverChristmas() &&
                    canApplyPrice(
                            reservation.getTotalPrice(),
                            10_000
                    ),
            reservation -> reservation.getDate() * 100 + 900,
            "크리스마스 디데이"),

    GIFT(
            reservation -> canApplyPrice(reservation.getTotalPrice(), 120_000),
            reservation -> 25_000,
            "증정"),

    SPECIAL(
            reservation ->
                    reservation.isSundayOrChristmas() &&
                    canApplyPrice(
                            reservation.getTotalPrice(),
                            10_000
                    ),
            reservation -> 1_000,
            "특별"),

    WEEKDAY(
            reservation ->
                    reservation.isWeekday() &&
                    canApplyPrice(
                            reservation.getTotalPrice(),
                            10_000
                    ),
            reservation -> reservation.getCountOfMainMenu() * 2_023,
            "평일"),

    WEEKEND(
            reservation ->
                    reservation.isWeekend() &&
                    canApplyPrice(
                            reservation.getTotalPrice(),
                            10_000
                    ),
            reservation -> reservation.getCountOfDessertMenu() * 2_023,
            "주말"),
    ;

    private final Function<Reservation, Boolean> canApply;
    private final Function<Reservation, Integer> calculateDiscountPrice;

    private final String name;

    Event(
            Function<Reservation, Boolean> canApply,
            Function<Reservation, Integer> calculateDiscountPrice,
            String name
    ) {
        this.canApply = canApply;
        this.calculateDiscountPrice = calculateDiscountPrice;
        this.name = name;
    }

    private static boolean canApplyPrice(int totalPurchasePrice, int minimumTotalPurchasePrice) {
        return totalPurchasePrice >= minimumTotalPurchasePrice;
    }

    public boolean canApply(final Reservation reservation) {
        return this.canApply.apply((reservation));
    }

    public int apply(final Reservation reservation) {
        return this.calculateDiscountPrice.apply(reservation);
    }

    public String getName() {
        return this.name;
    }
}
