package ivan.vatlin.hotel.dto;

public class Hotel {
    private String name;

    public Hotel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return name.equals(hotel.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
