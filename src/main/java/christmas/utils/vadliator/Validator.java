package christmas.utils.vadliator;

import java.util.List;
import christmas.exception.ExceptionCode;
import christmas.view.constant.Regex;

public class Validator {

    private final static Validator instance = new Validator();

    private Validator() {
    }

    public static Validator getInstance() {
        return instance;
    }

    /**
     * 0~9 중 하나로 이루어져 있지 않다면 예외 발생
     */
    public static void isOnlyNumber(final String target, ExceptionCode e) {
        if (!Regex.NUMBER.matches(target)) {
            e.throwException();
        }
    }

    /**
     * 리스트에 중복된 요소가 있다면 예외 발생
     */
    public static void isDuplication(final List<?> target, ExceptionCode e) {
        long count = target.stream()
                .distinct()
                .count();

        if (count != target.size()) {
            e.throwException();
        }
    }

    /**
     * 문자열에 중복된 문자가 있다면 예외 발생
     */
    public static void isDuplication(final String target, ExceptionCode e) {
        isDuplication(
                converToList(target),
                e
        );
    }

    /**
     * 주어진 숫자가 최대값보다 크거나 최솟값보다 작다면 예외 발생. 같다면 ok입니다.
     */
    public static void isValidRange(
            final int target,
            final int minimumSize,
            final int maximumSize,
            final ExceptionCode e
    ) {
        if (target < minimumSize || maximumSize < target) {
            e.throwException();
        }
    }

    /**
     * 리스트 길이가 최대값보다 크다면 예외 발생
     */
    public static void isOverSize(final List<?> target, final int maximumSize, final ExceptionCode e) {
        isValidSize(
                target,
                0,
                maximumSize,
                e
        );
    }

    /**
     * 문자열 길이가 최대값보다 크다면 예외 발생
     */
    public static void isOverSize(final String target, final int maximumSize, final ExceptionCode e) {
        isValidSize(
                target,
                0,
                maximumSize,
                e
        );
    }

    /**
     * 리스트 길이가 최대값보다 크거나 최솟값보다 작다면 예외 발생
     */
    public static void isValidSize(
            final List<?> target,
            final int minimumSize,
            final int maximumSize,
            final ExceptionCode e
    ) {
        isValidRange(
                target.size(),
                minimumSize,
                maximumSize,
                e
        );
    }

    /**
     * 리스트 길이가 주어진 값과 다르다면 예외 발생
     */
    public static void isValidSize(
            final List<?> target,
            final int validSize,
            final ExceptionCode e
    ) {
        isValidValue(
                target.size(),
                validSize,
                e
        );
    }

    /**
     * 문자열 길이가 최대값보다 크거나 최솟값보다 작다면 예외 발생
     */
    public static void isValidSize(
            final String target,
            final int minimumSize,
            final int maximumSize,
            final ExceptionCode e
    ) {
        isValidRange(
                target.length(),
                minimumSize,
                maximumSize,
                e
        );
    }

    public static void isValidSize(
            final String target,
            final int validSize,
            final ExceptionCode e
    ) {
        isValidValue(
                target.length(),
                validSize,
                e
        );
    }

    public static void isValidValue(
            final Number target,
            final Number validValue,
            final ExceptionCode e
    ) {
        if (!target.equals(validValue)) {
            e.throwException();
        }
    }

    public static void isNotSame(
            final Number target,
            final Number invalidValue,
            final ExceptionCode e
    ) {
        if (target.equals(invalidValue)) {
            e.throwException();
        }
    }

    public static void is(final Object o1, final Object o2, final ExceptionCode e) {
        if (o1 != o2) {
            e.throwException();
        }
    }

    public static void isNot(final Object o1, final Object o2, final ExceptionCode e) {
        if (o1 == o2) {
            e.throwException();
        }
    }

    public static void equals(final Object o1, final Object o2, final ExceptionCode e) {
        if (!o1.equals(o2)) {
            e.throwException();
        }
    }

    public static void notEquals(final Object o1, final Object o2, final ExceptionCode e) {
        if (o1.equals(o2)) {
            e.throwException();
        }
    }

    /**
     * 특정 요소가 포함되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static <E> void contains(List<E> target, E element, ExceptionCode e) {
        if (!target.contains(element)) {
            e.throwException();
        }
    }

    /**
     * 리스트가 오직 특정한 요소들로 이루어져 있는지 확인. 만약 특정한 요소 외의 것이 포함되어 있다면 예외 발생.
     * @param target 검증할 리스트
     * @param validElements 유효한 요소 요소
     */
    public static <E> void containsOnly(List<E> target, List<E> validElements, ExceptionCode e) {
        long countOfValidElementsInTarget = target.stream()
                .filter(validElements::contains)
                .count();

        if (countOfValidElementsInTarget != target.size()) {
            e.throwException();
        }
    }

    /**
     * 검증 대상이 특정 요소를 특정 개수보다 많이 포함하고 있다면 예외 발생
     * @param target 검증 대상 리스트
     * @param element 개수를 확인할 특정 요소
     * @param maximumCount 최대 허용 개수
     */
    public static <E> void containsOver(List<E> target, E element, long maximumCount, ExceptionCode e) {
        long countOfElementInTarget = target.stream()
                .filter(element::equals)
                .count();

        if (countOfElementInTarget < maximumCount) {
            e.throwException();
        }
    }

    /**
     * 특정 문자가 포함되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static void contains(String target, String element, ExceptionCode e) {
        contains(
                converToList(target),
                element,
                e
        );
    }

    /**
     * 특정 문자열이 정해진 문자들로만 이루어져 있지 않다면 예외 발생
     * @param target 검증 대상 문자열
     * @param validElements 유효한 구성 문자 리스트
     */
    public static void containsOnly(String target, List<Character> validElements, ExceptionCode e) {
        containsOnly(
                converToList(target),
                validElements.stream()
                        .map(String::valueOf)
                        .toList(),
                e
        );
    }

    /**
     * 문자열이 특정 요소를 특정 개수보다 많이 포함하고 있다면 예외 발생.
     * @param target 검증 대상 문자열
     * @param element 개수를 확인할 특정 요소
     * @param maximumCount 최대 허용 개수
     */
    public static void containsOver(String target, char element, long maximumCount, ExceptionCode e) {
        containsOver(
                converToList(target),
                String.valueOf(element),
                maximumCount,
                e
        );
    }

    /**
     * 특정 요소가 포함되어 있다면 예외가 발생. List.contains()를 사용한다.
     */
    public static <E> void notContains(final List<E> target, final E element, final ExceptionCode e) {
        if (target.contains(element)) {
            e.throwException();
        }
    }

    /**
     * 특정 문자가 포함되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static void notContains(final String target, final String element, final ExceptionCode e) {
        notContains(
                converToList(target),
                element,
                e
        );
    }

    private static List<String> converToList(String target) {
        return target.chars()
                .mapToObj(String::valueOf)
                .toList();
    }
}
