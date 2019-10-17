package ivan.vatlin.hotel.services;

import ivan.vatlin.hotel.dao.RequestsQueueDao;
import ivan.vatlin.hotel.entities.RequestConsumer;
import ivan.vatlin.hotel.entities.RequestProducer;
import ivan.vatlin.hotel.generators.HotelRequestGenerator;

public class HotelBookManager {
    private final int producerThreadQuantity;
    private final int consumerThreadQuantity;
    private RequestManager requestManager;
    private RequestsQueueDao requestsQueueDao;

    public HotelBookManager(int producersThreadQuantity, int consumersThreadQuantity,
                            RequestManager requestManager, RequestsQueueDao requestsQueueDao) {
        this.producerThreadQuantity = producersThreadQuantity;
        this.consumerThreadQuantity = consumersThreadQuantity;
        this.requestManager = requestManager;
        this.requestsQueueDao = requestsQueueDao;
    }

    public void run() {
        for (int i = 0; i < producerThreadQuantity; i++) {
            new Thread(new RequestProducer(new HotelRequestGenerator(), requestManager, requestsQueueDao)).start();
        }
//        for (int i = 0; i < consumerThreadQuantity; i++) {
//            new Thread(new RequestConsumer(requestsQueueDao)).start();
//        }
    }
}
