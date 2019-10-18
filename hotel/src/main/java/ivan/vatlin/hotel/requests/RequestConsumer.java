package ivan.vatlin.hotel.requests;

import ivan.vatlin.hotel.dao.RequestsBuffer;
import ivan.vatlin.hotel.dto.HotelBookRequest;
import ivan.vatlin.hotel.services.RequestController;

import java.util.logging.Logger;

public class RequestConsumer implements Runnable {
    private RequestsBuffer requestsBuffer;
    private RequestController requestController;
    private Logger logger = Logger.getGlobal();

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
                e.printStackTrace();
            }
            logger.info("Consumer (" + Thread.currentThread().getName() + "): processed " + hotelBookRequest);
        }
    }
}
