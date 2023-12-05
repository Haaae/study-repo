package christmas.controller;

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
    private final ChristmasService lottoService = ChristmasService.getInstance();

    public void run() {
    }
}
