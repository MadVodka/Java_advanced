package ivan.vatlin.hotel.dao;

import ivan.vatlin.hotel.dto.HotelBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class RequestsBuffer {
    private Deque<HotelBookRequest> requestsQueue;
    private int capacity;
    private Logger logger = LoggerFactory.getLogger(RequestsBuffer.class);

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
                logger.debug(String.format("Thread interrupted in trying to put request %n%s", e.getMessage()));
            }
        }

        if (requestsQueue.size() < capacity) {
            requestsQueue.add(hotelBookRequest);
            logger.info(String.format("Producer (%s): sent %s", Thread.currentThread().getName(), hotelBookRequest));
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
                logger.debug(String.format("Thread interrupted in trying to get request %n%s", e.getMessage()));
            }
        }

        HotelBookRequest hotelBookRequest = requestsQueue.pop();
        logger.info(String.format("Consumer (%s): received %s", Thread.currentThread().getName(), hotelBookRequest));
        notifyAll();
        return hotelBookRequest;
    }
}
