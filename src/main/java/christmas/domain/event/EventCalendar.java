package christmas.domain.event;

public enum EventCalendar {
    YEAR(2023),
    MONTH(12),
    START_DATE(1),
    END_DATE(31),
    CHRISTMAS(25);

    private final int value;

    EventCalendar(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
