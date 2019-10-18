package ivan.vatlin.hotel.requests;

import ivan.vatlin.hotel.dao.RequestsBuffer;
import ivan.vatlin.hotel.dto.HotelBookRequest;
import ivan.vatlin.hotel.generators.HotelRequestGenerator;
import ivan.vatlin.hotel.services.RequestController;

public class RequestProducer implements Runnable {
    private HotelRequestGenerator hotelRequestGenerator;
    private RequestController requestController;
    private RequestsBuffer requestsBuffer;

    public RequestProducer(HotelRequestGenerator hotelRequestGenerator, RequestController requestController,
                           RequestsBuffer requestsBuffer) {
        this.hotelRequestGenerator = hotelRequestGenerator;
        this.requestController = requestController;
        this.requestsBuffer = requestsBuffer;
    }

    @Override
    public void run() {
        while (requestController.canProducerProceed()) {
            HotelBookRequest hotelBookRequest = hotelRequestGenerator.generate();
            requestsBuffer.putRequest(hotelBookRequest);
        }
    }
}
