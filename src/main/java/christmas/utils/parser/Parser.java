package christmas.utils.parser;

import christmas.exception.ExceptionCode;
import christmas.utils.converter.Converter;
import christmas.utils.vadliator.Validator;
import christmas.view.constant.Regex;
import christmas.view.dto.OrderDto;
import java.util.List;

public class Parser {

    public static final int MENU_AND_COUNT_SIZE = 2;
    public static final int MENU_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex)
        );
    }

    public static List<OrderDto> parseToOrderDto(final String input, final ExceptionCode e) {
        List<String> menusWithCount = split(input, Regex.COMMA.getRegex());

        return menusWithCount.stream()
                .map(mwc -> mapToOrderDto(mwc, e))
                .toList();
    }

    private static OrderDto mapToOrderDto(final String menuWithCount, final ExceptionCode e) {
        List<String> menuAndCount = split(menuWithCount, Regex.BAR.getRegex());
        Validator.isValidSize(menuAndCount, MENU_AND_COUNT_SIZE, e);

        return new OrderDto(
                menuAndCount.get(MENU_INDEX).trim(),
                Converter.toInt(
                        menuAndCount.get(COUNT_INDEX).trim(),
                        e
                )
        );
    }
}
