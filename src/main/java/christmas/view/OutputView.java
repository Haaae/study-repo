package christmas.view;

import christmas.domain.Badge;
import christmas.domain.event.Event;
import christmas.service.dto.Preview;
import christmas.view.constant.Format;
import christmas.view.constant.Notice;
import christmas.view.constant.Regex;
import java.util.Map;

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

    public void printPreviewOfEvent(final Preview previewOfEvent) {
        Notice.PREVIEW_HEAD.print();

        printMenuAndCount(previewOfEvent.menunameWithCount());
        printTotalPurchasePrice(previewOfEvent.totalPurchasePrice());
        printGift(previewOfEvent.eventDiscountPrice(Event.GIFT));
        printEvent(previewOfEvent.eventsWithDiscountPrice());
        printTotalDiscountPrice(previewOfEvent.totalDiscountPrice());
        printTotalPriceAppliedEvent(previewOfEvent.totalPriceAppliedEvent());
        printBadge(previewOfEvent.badge());
    }

    private static void printBadge(final Badge badge) {
        Notice.PREVIEW_BADGE.print();

        System.out.println(
                badge.getName()
        );
    }

    private  void printMenuAndCount(final Map<String, Integer> menunameWithCount) {
        Notice.PREVIEW_MENU.print();

        menunameWithCount.keySet()
                .forEach(menuname ->
                                Format.menuAndCount.print(
                                        menuname,
                                        menunameWithCount.get(menuname)
                                )
                        );
    }

    private void printTotalPurchasePrice(final int totalPurchasePrice) {
        Notice.PREVIEW_TOTAL_PURCHASE_PRICE.print();
        Format.PRICE
                .print(
                        totalPurchasePrice
                );
    }

    private void printGift(final int giftEventDiscountPrice) {
        Notice.PREVIEW_GIFT.print();
        if (giftEventDiscountPrice != 0) {
            Notice.GIFT.print();
            return;
        }
        Notice.NOTING.print();
    }

    private void printEvent(final Map<Event, Integer> eventsWithDiscountPrice) {
        Notice.PREVIEW_EVENTS.print();

        if (eventsWithDiscountPrice.isEmpty()) {
            Notice.NOTING.print();
            return;
        }

        eventsWithDiscountPrice.keySet()
                .forEach(event ->
                        Format.EVENT.print(
                                event.getName(),
                                Regex.formatCashPrizeWithRounds(
                                        eventsWithDiscountPrice.get(event)
                                )
                        )
                );
    }

    private void printTotalDiscountPrice(final int totalDiscountPrice) {
        Notice.PREVIEW_TOTAL_DISCOUNT_PRICE.print();

        Format.TOTAL_DISCOUNT_PRICE.print(
                Regex.formatCashPrize(totalDiscountPrice)
        );
    }

    private void printTotalPriceAppliedEvent(final int totalPriceAppliedEvent) {
        Notice.PREVIEW_TOTAL_PRICE_APPLIED_EVENT.print();

        Format.PRICE.print(totalPriceAppliedEvent);
    }

}
