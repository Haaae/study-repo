package christmas.controller;

import christmas.domain.date.Date;
import christmas.domain.order.OrderBoard;
import christmas.exception.ExceptionCode;
import christmas.exception.ExceptionHandler;
import christmas.service.ChristmasService;
import christmas.service.dto.Preview;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.dto.OrderDto;
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
        // welcome 안내문구 출력
        outputView.printWelcomeNotice();

        // 방문 날짜 입력
        Date reservationDate = ExceptionHandler.handle(this::createReservationDateFromUserInput, ExceptionCode.INVALID_DATE);

        // 주문 입력
        OrderBoard orderBoard = ExceptionHandler.handle(this::createOrderBoardFromUserInput, ExceptionCode.INVALID_ORDER);


        // 이벤트 미리보기 받기
        Preview previewOfEvent = christmasService.getPreviewOfEvent(reservationDate, orderBoard);

        // 이벤트 적용 미리보기 출력
        outputView.printPreviewOfEvent(previewOfEvent);
    }

    private OrderBoard createOrderBoardFromUserInput(final ExceptionCode e) {
        List<OrderDto> orders = inputView.readOrder(e);
        return christmasService.createOrderBoard(orders, e);
    }

    private Date createReservationDateFromUserInput(final ExceptionCode e) {
        int date = inputView.readReservationDate(e);
        return christmasService.createDate(date, e);
    }
}
