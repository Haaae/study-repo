package christmas.domain;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum Badge {
    NONE(Integer.MIN_VALUE, 4_999),
    STAR(5_000, 9_999),
    TREE(10_000, 19_000),
    SANTA(20_000, Integer.MIN_VALUE),
    ;

    private final int startPrice;
    private final int endPrice;

    Badge(final int startPrice, final int endPrice) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }

    public static Badge from(final int totalDiscountPrice) {
        return Stream.of(values())
                .filter(badge -> badge.canApply(totalDiscountPrice))
                .findFirst()
                .orElse(NONE);
    }

    public boolean canApply(final int totalDiscountPrice) {
        return startPrice <= totalDiscountPrice && startPrice <= endPrice;
    }
}
