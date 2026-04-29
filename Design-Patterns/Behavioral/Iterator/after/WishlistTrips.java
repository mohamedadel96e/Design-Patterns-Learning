package after;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete aggregate backed by a dynamic list.
 */
public class WishlistTrips implements TripCollection {
    private final List<Trip> trips;

    public WishlistTrips() {
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    @Override
    public TripIterator createIterator() {
        return new WishlistTripsIterator(trips);
    }

    @Override
    public String getCollectionName() {
        return "Wishlist Trips";
    }
}
