package ivan.vatlin.hotel.services;

public class RequestController {
    private int maxQuantityRequests;
    private int producerCounter = 0;
    private int consumerCounter = 0;

    public RequestController(int maxQuantityRequests) {
        if (maxQuantityRequests < 0) {
            throw new IllegalArgumentException();
        }
        this.maxQuantityRequests = maxQuantityRequests;
    }

    public synchronized boolean canProducerProceed() {
        producerCounter++;
        return producerCounter <= maxQuantityRequests;
    }

    public synchronized boolean canConsumerProceed() {
        consumerCounter++;
        return consumerCounter <= maxQuantityRequests;
    }
}
