package ivan.vatlin.hotel.entities;

import ivan.vatlin.hotel.dao.RequestsQueueDao;
import ivan.vatlin.hotel.dto.HotelBookRequest;
import ivan.vatlin.hotel.generators.HotelRequestGenerator;
import org.apache.log4j.Logger;

public class RequestProducer implements Runnable {
    private HotelRequestGenerator hotelRequestGenerator;
    private RequestsQueueDao requestsQueueDao;
//    private int maxRequests;
//    private static int requestsCounter = 0;
    private Logger logger = Logger.getLogger(RequestProducer.class);

    public RequestProducer(HotelRequestGenerator hotelRequestGenerator, RequestsQueueDao requestsQueueDao) {
        this.hotelRequestGenerator = hotelRequestGenerator;
        this.requestsQueueDao = requestsQueueDao;
//        this.maxRequests = maxRequests;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            HotelBookRequest hotelBookRequest = hotelRequestGenerator.generate();
            boolean isAdded = requestsQueueDao.offer(hotelBookRequest);
            if (isAdded) {
                logger.info("Producer " + Thread.currentThread().getName() + ": sent " + hotelBookRequest);
            } else {
                logger.info("Producer " + Thread.currentThread().getName() + ": sent " + hotelBookRequest + ", but failed");
            }
        }
    }
}
