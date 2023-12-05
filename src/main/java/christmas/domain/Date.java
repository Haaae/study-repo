package christmas.domain;

import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;

public class Date {

    private final int date;

    public Date(final int date) {
        Validator.isValidRange(
                date,
                EventDate.START.getDate(),
                EventDate.END.getDate(),
                ExceptionCode.INVALID_DATE
        );

        this.date = date;
    }
}
