package before;

import java.util.List;

/**
 * BEFORE: Client logic is tightly coupled to each collection's internal structure.
 *
 * We have duplicated loops for arrays and lists, and every new operation repeats traversal code.
 */
public class ItineraryService {

    public void printBookedTrips(BookedTrips bookedTrips) {
        System.out.println("\n--- Booked Trips (Array Traversal) ---");

        Trip[] trips = bookedTrips.getTrips();
        int size = bookedTrips.getSize();
        if (size == 0) {
            System.out.println("No booked trips");
            return;
        }

        for (int i = 0; i < size; i++) {
            printTripDetails(trips[i]);
        }
    }

    public void printWishlistTrips(WishlistTrips wishlistTrips) {
        System.out.println("\n--- Wishlist Trips (List Traversal) ---");

        List<Trip> trips = wishlistTrips.getTrips();
        if (trips.isEmpty()) {
            System.out.println("No wishlist trips");
            return;
        }

        for (Trip trip : trips) {
            printTripDetails(trip);
        }
    }

    public void printBudgetFriendlyTrips(BookedTrips bookedTrips, WishlistTrips wishlistTrips) {
        System.out.println("\n--- Budget-friendly Trips (Duplicated Traversal Logic) ---");

        Trip[] bookedArray = bookedTrips.getTrips();
        for (int i = 0; i < bookedTrips.getSize(); i++) {
            Trip trip = bookedArray[i];
            if (trip != null && trip.isBudgetFriendly()) {
                printTripDetails(trip);
            }
        }

        List<Trip> wishlist = wishlistTrips.getTrips();
        for (Trip trip : wishlist) {
            if (trip.isBudgetFriendly()) {
                printTripDetails(trip);
            }
        }
    }

    public void printShortTrips(BookedTrips bookedTrips, WishlistTrips wishlistTrips, int maxDays) {
        System.out.println("\n--- Short Trips <= " + maxDays + " days (More Duplicated Traversal) ---");

        Trip[] bookedArray = bookedTrips.getTrips();
        for (int i = 0; i < bookedTrips.getSize(); i++) {
            Trip trip = bookedArray[i];
            if (trip != null && trip.getDurationDays() <= maxDays) {
                printTripDetails(trip);
            }
        }

        List<Trip> wishlist = wishlistTrips.getTrips();
        for (Trip trip : wishlist) {
            if (trip.getDurationDays() <= maxDays) {
                printTripDetails(trip);
            }
        }
    }

    private void printTripDetails(Trip trip) {
        System.out.printf("[%s] %-14s | %2d days | Cost: $%7.2f | Budget-friendly: %s%n",
                trip.getId(),
                trip.getDestination(),
                trip.getDurationDays(),
                trip.getEstimatedCost(),
                trip.isBudgetFriendly() ? "YES" : "NO");
    }
}
