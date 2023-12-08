package christmas.view;

import christmas.service.dto.EventPreviewDto;
import christmas.view.constant.Notice;

public class OutputView {
    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printWelcome() {
        Notice.NOTICE_WELCOME.print();
    }
    public void printPreview(EventPreviewDto eventPreviewDto) {

    }

}
