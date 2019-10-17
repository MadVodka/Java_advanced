package ivan.vatlin.hotel.services;

public class RequestManager {
    private int maxQuantityRequest;
    private int counter = 0;

    public RequestManager(int maxQuantityRequest) {
        this.maxQuantityRequest = maxQuantityRequest;
    }

    public boolean canProceed() {
        counter++;
        return counter < maxQuantityRequest;
    }
}
