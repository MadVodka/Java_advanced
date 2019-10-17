package ivan.vatlin.hotel;

import ivan.vatlin.hotel.dao.RequestsQueueDao;
import ivan.vatlin.hotel.services.HotelBookManager;
import ivan.vatlin.hotel.services.RequestManager;

public class Application {
    public static void main(String[] args) {
        RequestsQueueDao requestsQueueDao = new RequestsQueueDao(6);
        RequestManager requestManager = new RequestManager(20);
        HotelBookManager hotelBookManager = new HotelBookManager(4, 5,
                requestManager, requestsQueueDao);
        hotelBookManager.run();
    }
}
