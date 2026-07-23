package after;

import java.util.NoSuchElementException;

/**
 * Iterator for array-backed booked trips.
 */
public class BookedTripsIterator implements TripIterator {
    private final Trip[] trips;
    private final int size;
    private int position;

    public BookedTripsIterator(Trip[] trips, int size) {
        this.trips = trips;
        this.size = size;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < size;
    }

    @Override
    public Trip next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more booked trips");
        }
        return trips[position++];
    }
}
