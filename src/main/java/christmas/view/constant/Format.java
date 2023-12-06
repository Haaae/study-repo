package christmas.view.constant;


public enum Format {

    PRICE("%s원"),
    menuAndCount("%s %d개"),
    EVENT("%s 할인: -%s원"),
    TOTAL_DISCOUNT_PRICE("-%s원");

    private final String format;

    Format(final String format) {
        this.format = format;
    }

    public void print(Object... args) {
        System.out.printf(format + "\n", args);
    }
}
