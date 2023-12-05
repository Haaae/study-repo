package christmas.service;

public class ChristmasService {

    private static final ChristmasService instance = new ChristmasService();

    private ChristmasService() {
    }

    public static ChristmasService getInstance() {
        return instance;
    }
}
