package org.example.design_patterns.Behavioral.Iterator.after;

public class ShortTripFilter implements TripFilter {
    private final int maxDays;

    public ShortTripFilter(int maxDays) {
        this.maxDays = maxDays;
    }

    @Override
    public boolean matches(Trip trip) {
        return trip.getDurationDays() <= maxDays;
    }
}
