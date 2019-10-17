package ivan.vatlin.hotel.dao;

import ivan.vatlin.hotel.dto.HotelBookRequest;

import java.util.*;

public class RequestsQueueDao implements Queue<HotelBookRequest> {
    private List<HotelBookRequest> hotelBookRequests;
    private int tail = 0;

    public RequestsQueueDao(int size) {
        hotelBookRequests = new ArrayList<>(size);
    }

    @Override
    public synchronized int size() {
        return hotelBookRequests.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public synchronized boolean contains(Object o) {
        return hotelBookRequests.contains(o);
    }

    @Override
    public Iterator<HotelBookRequest> iterator() {
        return hotelBookRequests.iterator();
    }

    @Override
    public Object[] toArray() {
        return hotelBookRequests.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean add(HotelBookRequest hotelBookRequest) {
        if (hotelBookRequest == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            try {
                while (tail == size()) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tail < size()) {
                hotelBookRequests.add(hotelBookRequest);
                tail++;
                notifyAll();
                return true;
            }
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean offer(HotelBookRequest hotelBookRequest) {
        if (hotelBookRequest == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            try {
                while (tail == size()) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tail < size()) {
                hotelBookRequests.add(hotelBookRequest);
                tail++;
                notifyAll();
                return true;
            }
//            logger.info("Producer " + Thread.currentThread().getName() + ": sent " + hotelBookRequest + ", but failed");
            return false;
        }
    }

    @Override
    public synchronized HotelBookRequest remove() {
        while (tail == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tail--;
        notifyAll();

        return hotelBookRequests.remove(0);
    }

    @Override
    public synchronized HotelBookRequest poll() {
        while (tail == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tail--;
        notifyAll();

        return hotelBookRequests.remove(0);
    }

    @Override
    public synchronized HotelBookRequest element() {
        if (tail == 0) {
            throw new NoSuchElementException();
        }
        return hotelBookRequests.get(0);
    }

    @Override
    public synchronized HotelBookRequest peek() {
        if (tail == 0) {
            return null;
        }
        return hotelBookRequests.get(0);
    }
}
