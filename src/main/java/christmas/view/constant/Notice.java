package christmas.view.constant;

public enum Notice {

    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),

    RESERVATION_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (1 ~ 31 사이의 숫자만 입력해주세요!)"),
    ORDERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. 메뉴 개수가 20개를 초과하거나, 음료만 주문하지 않도록 유의해주세요. 총주문금액 10,000원 이상부터 이벤트가 적용됩니다!\n"
            + "(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String notice;

    Notice(final String notice) {
        this.notice = notice;
    }

    public void print() {
        System.out.println(notice);
    }
}
