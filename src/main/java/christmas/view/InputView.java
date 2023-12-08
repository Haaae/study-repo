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

    public int readReserveDate(ExceptionCode e) {
        Notice.DATE.print();

        return Converter.toInt(
                Console.readLine(),
                e
        );
    }

    public List<OrderDto> readOrders(ExceptionCode e) {
        return Parser.parseToOrderDtos(
                Console.readLine(),
                e
        );
    }

}
