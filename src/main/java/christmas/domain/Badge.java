package christmas.domain;

import java.util.stream.Stream;

public enum Badge {
    NONE(Integer.MIN_VALUE, 4_999, "없음"),
    STAR(5_000, 9_999, "별"),
    TREE(10_000, 19_000, "트리"),
    SANTA(20_000, Integer.MIN_VALUE, "산타"),
    ;
    
    private final int startPrice;
    private final int endPrice;
    private final String name;

    Badge(final int startPrice, final int endPrice, String name) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.name = name;
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

    public String getName() {
        return this.name;
    }
}
