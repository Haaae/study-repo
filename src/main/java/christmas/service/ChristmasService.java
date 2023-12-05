package christmas.service;

import christmas.domain.Date;

public class ChristmasService {

    private static final ChristmasService instance = new ChristmasService();

    private ChristmasService() {
    }

    public static ChristmasService getInstance() {
        return instance;
    }

    public Date createDate(int date) {
        return new Date(date);
    }
}
