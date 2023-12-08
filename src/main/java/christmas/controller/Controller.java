package christmas.controller;

import christmas.service.DomainService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private static final Controller instance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private final DomainService lottoService = DomainService.getInstance();

    public void run() {
    }
}
