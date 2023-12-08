package christmas.utils.parser;

import christmas.exception.ExceptionCode;
import christmas.utils.converter.Converter;
import christmas.view.constant.Regex;
import christmas.view.dto.OrderDto;
import java.util.List;

public class Parser {

    public static final int MENU_INDEX = 0;
    public static final int COUNT_INDEX = 1;

    public static List<String> split(final String string, final String regex) {
        return List.of(
                string.split(regex)
        );
    }

    public static List<OrderDto> parseToOrderDtos(String input, ExceptionCode e) {

        List<String> menuAndCount = split(input, Regex.COMMA.getRegex());

        return menuAndCount.stream()
                .map(mac -> parseToOrderDto(mac, e))
                .toList();
    }

    public static OrderDto parseToOrderDto(String menuAndCount, ExceptionCode e) {
        List<String> split = split(menuAndCount, Regex.BAR.getRegex());
        return new OrderDto(
                split.get(MENU_INDEX),
                Converter.toInt(
                        split.get(COUNT_INDEX),
                        e
                )
        );
    }
}
