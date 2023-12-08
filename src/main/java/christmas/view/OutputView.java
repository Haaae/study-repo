package christmas.view;

import christmas.domain.badge.Badge;
import christmas.domain.event.Event;
import christmas.service.dto.EventPreviewDto;
import christmas.view.constant.Format;
import christmas.view.constant.Notice;
import christmas.view.constant.Regex;
import java.util.List;
import java.util.Map;

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
        printHead(eventPreviewDto);
        printMenu(eventPreviewDto.menuWithCount());
        printTotalPurchasePrice(eventPreviewDto.totalPurchasePrice());
        printGift(eventPreviewDto.gifts());
        printEvent(eventPreviewDto.eventWithDiscountPrice());
        printTotalDiscountPrice(
                eventPreviewDto.eventWithDiscountPrice(),
                eventPreviewDto.totalDiscountPrice()
        );
        printPriceAppliedByEvent(eventPreviewDto.totalPriceAppliedByEvent());
        printBadge(eventPreviewDto.badge());
    }

    private void printHead(EventPreviewDto eventPreviewDto) {
        Format.PREVIEW_HEAD
                .print(eventPreviewDto.date());
    }

    private void printMenu(Map<String, Integer> menuWithCount) {
        System.out.println();
        Notice.PREVIEW_MENU.print();
        menuWithCount.forEach(Format.PREVIEW_MENU::print);
    }

    private void printTotalPurchasePrice(int totalPurchasePrice) {
        System.out.println();
        Notice.PREVIEW_TOTAL_PURCHASE_PRICE.print();
        Format.PRICE
                .print(
                        Regex.formatCashPrize(totalPurchasePrice)
                );
    }

    private void printGift(List<String> gifts) {
        System.out.println();
        Notice.PREVIEW_GIFT.print();

        if (gifts.isEmpty()) {
            Notice.NOTING.print();
            return;
        }

        gifts
                        .forEach(Format.GIFT::print);
    }

    private void printEvent(Map<Event, Integer> eventWithDiscountPrice) {
        System.out.println();
        Notice.PREVIEW_EVENT.print();

        if (eventWithDiscountPrice.isEmpty()) {
            Notice.NOTING.print();
            return;
        }

        eventWithDiscountPrice.forEach((event, price) ->
                        Format.EVENT.print(
                                event,
                                Regex.formatCashPrize(price)
                        )
                );
    }

    private void printTotalDiscountPrice(Map<Event, Integer> eventWithDiscountPrice, int totalDiscountPrice) {
        System.out.println();
        Notice.PREVIEW_TOTAL_DISCOUNT_PRICE.print();

        if (eventWithDiscountPrice.isEmpty()) {
            Notice.NOTING.print();
            return;
        }

        Format.TOTAL_DISCOUNT_PRICE.print(
                Regex.formatCashPrize(totalDiscountPrice)
        );
    }

    private void printPriceAppliedByEvent(int totalPriceAppliedByEvent) {
        System.out.println();
        Notice.PREVIEW_PRICE_APPLIED_BY_EVENT.print();
        Format.PRICE.print(
                Regex.formatCashPrize(totalPriceAppliedByEvent)
        );
    }

    private void printBadge(Badge badge) {
        System.out.println();
        Notice.PREVIEW_BADGE.print();
        Format.BADGE.print(
                badge.getName()
        );
    }

}
