package org.example.design_patterns.Behavioral.Iterator.before;

import java.util.ArrayList;
import java.util.List;

/**
 * A dynamic collection implemented with List.
 *
 * Problem in before version: callers must know and depend on List traversal logic.
 */
public class WishlistTrips {
    private final List<Trip> trips;

    public WishlistTrips() {
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
