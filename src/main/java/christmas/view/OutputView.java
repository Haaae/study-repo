package christmas.view;

import christmas.view.constant.Notice;

public class OutputView {
    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printWelcomeNotice() {
        Notice.WELCOME.print();
    }
}
