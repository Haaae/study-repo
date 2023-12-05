package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionCode;
import christmas.utils.converter.Converter;
import christmas.view.constant.Notice;

public class InputView {
    private static final InputView instance = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public int readReservationDate() {
        Notice.RESERVATION_DATE.print();

        return Converter.toInt(
                Console.readLine(),
                ExceptionCode.INVALID_DATE
        );
    }
}
