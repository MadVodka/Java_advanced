package ivan.vatlin.hotel.dataholder;

import java.util.Arrays;
import java.util.List;

public class HotelDataHolder {
    private static final String[] HOTEL_NAMES_ARR = {"Park Inn", "Central Hotel", "Radisson", "Mariott", "Caesars Palace",
            "Bellagio", "Luxor", "The Mirage", "Borgata"};

    public static final List<String> HOTEL_NAMES = Arrays.asList(HOTEL_NAMES_ARR);

    private HotelDataHolder() {
    }
}
