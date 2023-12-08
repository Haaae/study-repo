package christmas.view.constant;


public enum Format {

    PRICE("%s원"),
    PREVIEW_HEAD("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    PREVIEW_MENU("%s %d개"),
    GIFT("%s"),
    EVENT("%s: -%s원"),
    TOTAL_DISCOUNT_PRICE("-%s원"),
    BADGE("%s"),
    ;

    private final String format;

    Format(final String format) {
        this.format = format;
    }

    public void print(Object... args) {
        System.out.printf(format + "\n", args);
    }
}
