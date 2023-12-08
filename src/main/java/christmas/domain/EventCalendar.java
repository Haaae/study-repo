package christmas.domain;

public enum EventCalendar {
    YEAR(2023),
    MONTH(12),
    START_DATE(1),
    END_DATE(31)
    ;

    private final int value;

    EventCalendar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
