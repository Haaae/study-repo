package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionCode;
import christmas.utils.converter.Converter;
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

    public int readReserveDate() {
        Notice.DATE.print();

        return Converter.toInt(
                Console.readLine(),
                ExceptionCode.INVALID_DATE
        );
    }

    public List<OrderDto> readOrders() {
    }

}
