package christmas.domain;

import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReservationDate {

    private final LocalDate date;

    public ReservationDate(int date, ExceptionCode e) {
        Validator.isValidRange(
                date,
                EventCalendar.START_DATE.getValue(),
                EventCalendar.END_DATE.getValue(),
                e);

        this.date = LocalDate.of(
                EventCalendar.YEAR.getValue(),
                EventCalendar.MONTH.getValue(),
                date
        );
    }

    public boolean isNotOverChristmas() {
        int day = getDate();

        return EventCalendar.START_DATE.getValue() <= day &&
                day <= EventCalendar.CHRISTMAS.getValue();
    }

    public boolean isChristmas() {
        return EventCalendar.CHRISTMAS.getValue() == getDate();
    }

    public boolean isSunday() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY ||
                dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public int getDate() {
        return date.getDayOfMonth();
    }
}
