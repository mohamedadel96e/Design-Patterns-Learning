package after;

/**
 * Concrete aggregate backed by a fixed array.
 */
public class BookedTrips implements TripCollection {
    private static final int MAX_TRIPS = 20;

    private final Trip[] trips;
    private int size;

    public BookedTrips() {
        this.trips = new Trip[MAX_TRIPS];
        this.size = 0;
    }

    public void addTrip(Trip trip) {
        if (size >= MAX_TRIPS) {
            throw new IllegalStateException("Booked trips storage is full");
        }
        trips[size++] = trip;
    }

    @Override
    public TripIterator createIterator() {
        return new BookedTripsIterator(trips, size);
    }

    @Override
    public String getCollectionName() {
        return "Booked Trips";
    }
}
