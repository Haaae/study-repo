package christmas.controller;

import christmas.domain.date.ReservationDate;
import christmas.domain.order.OrderBoard;
import christmas.exception.ExceptionCode;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.service.dto.EventPreviewDto;
import christmas.view.InputView;
import christmas.view.dto.OrderDto;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {

    private static final ChristmasController instance = new ChristmasController();

    private ChristmasController() {
    }

    public static ChristmasController getInstance() {
        return instance;
    }

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final ChristmasService christmasService = ChristmasService.getInstance();

    public void run() {
        outputView.printWelcome();

        // 날짜 입력받아 생성
        ReservationDate date = ExceptionHandler.handle(this::createDateFromUserInput, ExceptionCode.INVALID_DATE);

        // 주문 입력받아 생성
        OrderBoard orderBoard = ExceptionHandler.handle(this::createOrderBoardFromUserInput, ExceptionCode.INVALID_ORDER);

        // 날짜와 주문으로 이벤트 미리보기 생성 및 출력
        EventPreviewDto eventPreviewDto = christmasService.getPreview(date, orderBoard);
        outputView.printPreview(eventPreviewDto);
    }

    private ReservationDate createDateFromUserInput(ExceptionCode e) {
        int reserveDate = inputView.readReserveDate(e);
        return new ReservationDate(reserveDate, e);
    }

    private OrderBoard createOrderBoardFromUserInput(ExceptionCode e) {
        List<OrderDto> orders = inputView.readOrders(e);
        return new OrderBoard(orders, e);
    }
}
