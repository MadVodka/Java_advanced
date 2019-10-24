package ivan.vatlin.hotel.generators;

import ivan.vatlin.hotel.dto.Hotel;
import ivan.vatlin.hotel.dto.HotelBookRequest;

import java.time.LocalDate;
import java.util.Random;

import static ivan.vatlin.hotel.dataholder.HotelDataHolder.HOTEL_NAMES;

public class HotelRequestGenerator {
    private Random random = new Random();

    public HotelBookRequest generate() {
        String hotelName = HOTEL_NAMES.get(random.nextInt(HOTEL_NAMES.size()));
        Hotel hotel = new Hotel(hotelName);
        return new HotelBookRequest(hotel, LocalDate.now());
    }
}
