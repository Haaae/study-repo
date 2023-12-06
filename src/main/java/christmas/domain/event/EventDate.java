package christmas.domain.event;

public enum EventDate {

    START(1),
    END(31),
    CHRISTMAS(25),
    FIRST_SATURDAY(2),
    FIRST_SUNDAY(3),

    ;

    public static final int COUNT_OF_DAY_IN_WEEK = 7;
    private final int date;

    EventDate(final int date) {
        this.date = date;
    }

    public static boolean isNotOverChristmas(final int date) {
        return EventDate.START.date <= date &&
                date <= EventDate.CHRISTMAS.date;
    }

    public static boolean isSundayOrChristmas(final int date) {
        return isSunday(date) || isChristmas(date);
    }

    public static boolean isWeekend(final int date) {
        return isSaturday(date) || isSunday(date);
    }

    public static boolean isWeekday(final int date) {
        return !isWeekend(date);
    }

    private static boolean isSunday(final int date) {
        return date % COUNT_OF_DAY_IN_WEEK == FIRST_SUNDAY.date;
    }

    private static boolean isSaturday(final int date) {
        return date % COUNT_OF_DAY_IN_WEEK == FIRST_SATURDAY.date;
    }

    private static boolean isChristmas(final int date) {
        return CHRISTMAS.date == date;
    }

    public int getDate() {
        return date;
    }
}
