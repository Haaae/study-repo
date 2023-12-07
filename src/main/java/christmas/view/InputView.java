package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionCode;
import christmas.utils.converter.Converter;
import christmas.utils.parser.Parser;
import christmas.view.constant.Notice;
import christmas.view.dto.OrderDto;
import java.util.List;

public class InputView {
    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int readReservationDate(final ExceptionCode e) {
        Notice.RESERVATION_DATE.print();

        return Converter.toInt(
                Console.readLine(),
                e
        );
    }

    public List<OrderDto> readOrder(final ExceptionCode e) {
        Notice.ORDERS.print();

        return Parser.parseToOrderDto(
                Console.readLine(),
                e
        );
    }
}
