package christmas.domain;

import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;
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
}
