package christmas.domain;

public enum EventDate {

    START(1),
    END(31),
    CHRISTMAS(25),
    ;

    private final int date;

    EventDate(final int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
