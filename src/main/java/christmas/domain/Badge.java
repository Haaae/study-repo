package christmas.domain;

import java.util.stream.Stream;

public enum Badge {
    NOTING(Integer.MIN_VALUE, 4_999, "없음"),
    STAR(5_000, 9_999, "별"),
    TREE(10_000, 19_999, "트리"),
    SANTA(20_000, Integer.MAX_VALUE, "산타"),
    ;

    private final int minPrice;
    private final int maxPrice;
    private final String name;

    Badge(int minPrice, int maxPrice, String name) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.name = name;
    }

    public static Badge from(int discountPrice) {
        return Stream.of(values())
                .filter(badge -> badge.minPrice <= discountPrice && discountPrice <= badge.maxPrice)
                .findFirst()
                .orElse(NOTING);
    }

    public String getName() {
        return name;
    }
}
