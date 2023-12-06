package christmas.domain.reservation;

import christmas.domain.date.Date;
import christmas.domain.order.OrderBoard;
import java.util.Map;

public class Reservation {

    private final Date reservationDate;
    private final OrderBoard orderBoard;

    public Reservation(final Date reservationDate, final OrderBoard orderBoard) {
        this.reservationDate = reservationDate;
        this.orderBoard = orderBoard;
    }


    public int getDate() {
        return reservationDate.getDate();
    }

    public int getTotalPrice() {
        return orderBoard.getTotalPrice();
    }

    public int getCountOfMainMenu() {
        return orderBoard.getCountOfMainMenu();
    }

    public int getCountOfDessertMenu() {
        return orderBoard.getCountOfDessertMenu();
    }

    public Map<String, Integer> getMenunameWithCount() {
        return orderBoard.getMenunameWithCount();
    }
}
