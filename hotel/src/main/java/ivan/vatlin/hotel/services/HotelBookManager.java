package ivan.vatlin.hotel.services;

import ivan.vatlin.hotel.dao.RequestsQueueDao;
import ivan.vatlin.hotel.entities.RequestConsumer;
import ivan.vatlin.hotel.entities.RequestProducer;
import ivan.vatlin.hotel.generators.HotelRequestGenerator;

public class HotelBookManager {
    private final int producerThreadQuantity;
    private final int consumerThreadQuantity;
    private int requestsQuantity;
    private RequestsQueueDao requestsQueueDao;

    public HotelBookManager(int producersThreadQuantity, int consumersThreadQuantity, int requestsQuantity, RequestsQueueDao requestsQueueDao) {
        this.producerThreadQuantity = producersThreadQuantity;
        this.consumerThreadQuantity = consumersThreadQuantity;
        this.requestsQuantity = requestsQuantity;
        this.requestsQueueDao = requestsQueueDao;
    }

    public void run() {
        for (int i = 0; i < producerThreadQuantity; i++) {
            new Thread(new RequestProducer(new HotelRequestGenerator(), requestsQueueDao)).start();
        }
        for (int i = 0; i < consumerThreadQuantity; i++) {
            new Thread(new RequestConsumer(requestsQueueDao)).start();
        }
    }
}
