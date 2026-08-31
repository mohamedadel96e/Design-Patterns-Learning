package org.example.design_patterns.Behavioral.Iterator.before;

/**
 * A fixed-size storage that simulates a legacy collection API.
 *
 * Problem in before version: the internal array is exposed to clients.
 */
public class BookedTrips {
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

    public Trip[] getTrips() {
        return trips;
    }

    public int getSize() {
        return size;
    }
}
