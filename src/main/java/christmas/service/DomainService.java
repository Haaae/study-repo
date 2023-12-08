package christmas.service;

public class DomainService {

    private static final DomainService instance = new DomainService();

    private DomainService() {
    }

    public static DomainService getInstance() {
        return instance;
    }
}
