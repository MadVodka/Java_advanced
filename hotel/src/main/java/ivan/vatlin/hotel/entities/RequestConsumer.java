package ivan.vatlin.hotel.entities;

import ivan.vatlin.hotel.dao.RequestsQueueDao;
import ivan.vatlin.hotel.dto.HotelBookRequest;
import org.apache.log4j.Logger;

public class RequestConsumer implements Runnable {
    private RequestsQueueDao requestsQueueDao;
    private Logger logger = Logger.getLogger(RequestConsumer.class);

    public RequestConsumer(RequestsQueueDao requestsQueueDao) {
        this.requestsQueueDao = requestsQueueDao;
    }

    @Override
    public void run() {
        HotelBookRequest hotelBookRequest = requestsQueueDao.poll();
        logger.info("Booker " + Thread.currentThread().getName() + ": received " + hotelBookRequest);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Producer " + Thread.currentThread().getName() + ": processed " + hotelBookRequest);
    }
}
