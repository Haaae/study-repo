package christmas.controller;

import christmas.domain.Date;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

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

        // 주문 입력

        // 예약 생성

        // 적용 가능 이벤트 적용

        // 이벤트 배지 결정

        // 이벤트 적용 미리보기 출력


    }
}
