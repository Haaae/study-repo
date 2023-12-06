package christmas.domain.date;

import christmas.domain.event.EventDate;
import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;

public class Date {

    private final int date;

    public Date(final int date, final ExceptionCode e) {
        Validator.isValidRange(
                date,
                EventDate.START.getDate(),
                EventDate.END.getDate(),
                e
        );

        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
