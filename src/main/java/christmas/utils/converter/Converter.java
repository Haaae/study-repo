package christmas.utils.converter;

import christmas.exception.ExceptionCode;
import christmas.utils.vadliator.Validator;

public class Converter {

    public static long toLong(final String input, final ExceptionCode e) {
        Validator.isOnlyNumber(input, e);
        return Long.parseLong(input);
    }

    public static int toInt(final String input, final ExceptionCode e) {
        Validator.isOnlyNumber(input, e);
        return Integer.parseInt(input);
    }
}
