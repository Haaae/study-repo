package christmas.controller;

import christmas.domain.Date;
import christmas.domain.OrderBoard;
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
        Date date = ExceptionHandler.handle(this::createDateFromUserInput);

        // 주문 입력받아 생성
        OrderBoard orderBoard = ExceptionHandler.handle(this::createOrderBoardFromUserInput);

        // 날짜와 주문으로 이벤트 미리보기 생성 및 출력
        EventPreviewDto eventPreviewDto = christmasService.getPreview(date, orderBoard);
        outputView.printPreview(eventPreviewDto);
    }

    private Date createDateFromUserInput() {
        int reserveDate = inputView.readReserveDate();
        return new Date(reserveDate);
    }

    private OrderBoard createOrderBoardFromUserInput() {
        List<OrderDto> orders = inputView.readOrders();
        return new OrderBoard(orders);
    }
}
