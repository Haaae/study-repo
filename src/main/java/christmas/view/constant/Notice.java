package christmas.view.constant;

public enum Notice {

    NOTICE_WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),

    DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (1 ~ 31 범위의 숫자만 입력해 주세요!)"),
    ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요."
            + "\n이벤트 적용 최소금액은 1만원입니다. 음료만 주문하거나, 메뉴 개수가 20개가 넘거나, 메뉴가 중복되지 않도록 유의해주세요! "
            + "\n(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PREVIEW_MENU("<주문 메뉴>"),
    PREVIEW_TOTAL_PURCHASE_PRICE("<할인 전 총주문 금액>"),
    PREVIEW_GIFT("<증정 메뉴>"),
    PREVIEW_EVENT("<혜택 내역>"),
    PREVIEW_TOTAL_DISCOUNT_PRICE("<총혜택 금액>"),
    PREVIEW_PRICE_APPLIED_BY_EVENT("<할인 후 예상 결제 금액>"),
    PREVIEW_BADGE("<12월 이벤트 배지>"),
    NOTING("없음");

    private final String notice;

    Notice(final String notice) {
        this.notice = notice;
    }

    public void print() {
        System.out.println(notice);
    }
}
