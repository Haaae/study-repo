package christmas.view.constant;

public enum Notice {

    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),

    RESERVATION_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (1 ~ 31 사이의 숫자만 입력해주세요!)"),
    ORDERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. 메뉴 개수가 20개를 초과하거나, 음료만 주문하지 않도록 유의해주세요. 총주문금액 10,000원 이상부터 이벤트가 적용됩니다!\n"
            + "(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_HEAD("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    PREVIEW_MENU("\n<주문 메뉴>"),
    PREVIEW_TOTAL_PURCHASE_PRICE("\n<할인 전 총주문 금액>"),
    PREVIEW_GIFT("\n<증정 메뉴>"),
    PREVIEW_EVENTS("\n<혜택 내역>"),
    PREVIEW_TOTAL_DISCOUNT_PRICE("\n<총혜택 금액>"),
    PREVIEW_TOTAL_PRICE_APPLIED_EVENT("\n<할인 후 예상 결제 금액>"),
    PREVIEW_BADGE("\n<12월 이벤트 배지>"),
    NOTING("없음"),
    GIFT("샴페인 1개");

    private final String notice;

    Notice(final String notice) {
        this.notice = notice;
    }

    public void print() {
        System.out.println(notice);
    }
}
