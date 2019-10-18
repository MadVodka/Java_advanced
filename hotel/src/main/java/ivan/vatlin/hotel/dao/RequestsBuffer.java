package ivan.vatlin.hotel.dao;

import ivan.vatlin.hotel.dto.HotelBookRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

public class RequestsBuffer {
    private Deque<HotelBookRequest> requestsQueue;
    private int capacity;
    private Logger logger = Logger.getGlobal();

    public RequestsBuffer(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        requestsQueue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public synchronized boolean putRequest(HotelBookRequest hotelBookRequest) {
        while (capacity == requestsQueue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (requestsQueue.size() < capacity) {
            requestsQueue.add(hotelBookRequest);
            logger.info("Producer (" + Thread.currentThread().getName() + "): sent " + hotelBookRequest);
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized HotelBookRequest getRequest() {
        while (requestsQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        HotelBookRequest hotelBookRequest = requestsQueue.pop();
        logger.info("Consumer (" + Thread.currentThread().getName() + "): received " + hotelBookRequest);
        notifyAll();
        return hotelBookRequest;
    }
}
