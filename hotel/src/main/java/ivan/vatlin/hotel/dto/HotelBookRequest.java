package ivan.vatlin.hotel.dto;

import java.time.LocalDate;

public class HotelBookRequest {
    private static long counter = 1;
    private final long id = counter++;
    private Hotel hotel;
    private LocalDate localDate;

    public HotelBookRequest(Hotel hotel, LocalDate localDate) {
        this.hotel = hotel;
        this.localDate = localDate;
    }

    public long getId() {
        return id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public String toString() {
        return "HotelBookRequest{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", localDate=" + localDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelBookRequest that = (HotelBookRequest) o;

        if (id != that.id) return false;
        if (!localDate.equals(that.localDate)) return false;
        return hotel.equals(that.hotel);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + localDate.hashCode();
        result = 31 * result + hotel.hashCode();
        return result;
    }
}
