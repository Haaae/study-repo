package christmas.domain;

@FunctionalInterface
public interface Discountable {
    int discount(int value);
}
