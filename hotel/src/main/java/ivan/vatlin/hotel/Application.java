package ivan.vatlin.hotel;

import ivan.vatlin.hotel.dao.RequestsBuffer;
import ivan.vatlin.hotel.generators.HotelRequestGenerator;
import ivan.vatlin.hotel.requests.RequestConsumer;
import ivan.vatlin.hotel.requests.RequestProducer;
import ivan.vatlin.hotel.services.RequestController;

public class Application {
    public static void main(String[] args) {
        final int producerThreadQuantity = 4;
        final int consumerThreadQuantity = 5;
        final int bufferCapacity = 8;
        final int maxQuantityRequests = 40;

        RequestsBuffer requestsBuffer = new RequestsBuffer(bufferCapacity);
        RequestController requestController = new RequestController(maxQuantityRequests);

        for (int i = 0; i < producerThreadQuantity; i++) {
            RequestProducer requestProducer = new RequestProducer(new HotelRequestGenerator(),
                    requestController, requestsBuffer);
            new Thread(requestProducer).start();
        }
        for (int i = 0; i < consumerThreadQuantity; i++) {
            RequestConsumer requestConsumer = new RequestConsumer(requestsBuffer, requestController);
            new Thread(requestConsumer).start();
        }
    }
}
