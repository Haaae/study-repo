package christmas.domain.event;

import christmas.domain.Discountable;
import christmas.domain.reservation.Reservation;

public enum Event {
    CHRISTMAS(10_000, date -> date * 100 + 1_000, 1_000) {
        @Override
        public boolean canApply(Reservation reservation) {
            return EventDate.isNotOverChristmas(reservation.getDate()) &&
                    canApplyPrice(reservation.getTotalPrice());
        }

        @Override
        public int apply(Reservation reservation) {
            // 날짜에 따른 할인
            return calculateDiscountPrice(reservation.getDate());
        }
    },

    GIFT(120_000, price -> 25_000, 25_000) {
        @Override
        public boolean canApply(Reservation reservation) {
            return canApplyPrice(reservation.getTotalPrice());
        }

        /**
         * 증정 이벤트는 가격이나 메뉴 혹은 날짜에 따라 할인 금액이 변하지 않으므로 calculateDiscountPrice() 함수에 아무 값이나 넣어준다.
         * @param reservation
         * @return
         */
        @Override
        public int apply(Reservation reservation) {
            return calculateDiscountPrice(reservation.getDate());
        }
    },

    SPECIAL(10_000, price -> 1_000, 1_000) {
        @Override
        public boolean canApply(Reservation reservation) {
            return EventDate.isSundayOrChristmas(reservation.getDate()) &&
                    canApplyPrice(reservation.getTotalPrice());
        }

        @Override
        public int apply(Reservation reservation) {
            return calculateDiscountPrice(reservation.getDate());
        }
    },

    WEEKDAY(10_000, countOfMainMenu -> countOfMainMenu * 2_023, 2_023) {
        @Override
        public boolean canApply(Reservation reservation) {
            return EventDate.isWeekday(reservation.getDate()) &&
                    canApplyPrice(reservation.getTotalPrice());
        }

        @Override
        public int apply(Reservation reservation) {
            return calculateDiscountPrice(reservation.getCountOfDessertMenu());
        }
    },

    WEEKEND(10_000, countOfDessertMenu -> countOfDessertMenu * 2_023, 2_023) {
        @Override
        public boolean canApply(Reservation reservation) {
            return EventDate.isWeekend(reservation.getDate()) &&
                    canApplyPrice(reservation.getTotalPrice());
        }

        @Override
        public int apply(Reservation reservation) {
            return calculateDiscountPrice(reservation.getCountOfMainMenu());
        }
    },
    ;

    private final int minimumTotalPurchasePrice;
    private final Discountable discountable;
    private final int discountPrice;

    Event(int minimumTotalPurchasePrice, Discountable discountable, int discountPrice) {
        this.minimumTotalPurchasePrice = minimumTotalPurchasePrice;
        this.discountable = discountable;
        this.discountPrice = discountPrice;
    }

    public abstract boolean canApply(final Reservation reservation);
    public abstract int apply(final Reservation reservation);

    public boolean canApplyPrice(int totalPurchasePrice) {
        return totalPurchasePrice >= this.minimumTotalPurchasePrice;
    }

    public int calculateDiscountPrice(int value) {
        return this.discountable.discount(value);
    }
}
