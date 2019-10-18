package ivan.vatlin.hotel.requests;

import ivan.vatlin.hotel.dao.RequestsBuffer;
import ivan.vatlin.hotel.dto.HotelBookRequest;
import ivan.vatlin.hotel.services.RequestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestConsumer implements Runnable {
    private RequestsBuffer requestsBuffer;
    private RequestController requestController;
    private Logger logger = LoggerFactory.getLogger(RequestConsumer.class);

    public RequestConsumer(RequestsBuffer requestsBuffer, RequestController requestController) {
        this.requestsBuffer = requestsBuffer;
        this.requestController = requestController;
    }

    @Override
    public void run() {
        while (requestController.canConsumerProceed()) {
            HotelBookRequest hotelBookRequest = requestsBuffer.getRequest();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.debug(String.format("Thread interrupted during processing request by consumer %n%s", e.getMessage()));
            }
            logger.info(String.format("Consumer (%s): processed %s", Thread.currentThread().getName(), hotelBookRequest));
        }
    }
}
