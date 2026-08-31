package org.example.design_patterns.Behavioral.Iterator.after;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator for list-backed wishlist trips.
 */
public class WishlistTripsIterator implements TripIterator {
    private final List<Trip> trips;
    private int position;

    public WishlistTripsIterator(List<Trip> trips) {
        this.trips = trips;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < trips.size();
    }

    @Override
    public Trip next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more wishlist trips");
        }
        return trips.get(position++);
    }
}
