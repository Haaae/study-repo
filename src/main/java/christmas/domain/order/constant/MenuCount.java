package christmas.domain.order.constant;

public enum MenuCount {

    MAXIMUM(20),
    MINIMUM(1),

    ;

    private final int value;

    MenuCount(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
