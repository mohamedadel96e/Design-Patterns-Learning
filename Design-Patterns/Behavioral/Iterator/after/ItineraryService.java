package after;

/**
 * AFTER: Client works only with iterator abstractions.
 *
 * The service does not care if data comes from arrays, lists, or any future structure.
 */
public class ItineraryService {

    public void printTrips(TripCollection collection) {
        System.out.println("\n--- " + collection.getCollectionName() + " (Unified Traversal) ---");
        printFromIterator(collection.createIterator());
    }

    public void printFilteredTrips(TripCollection collection, TripFilter filter, String title) {
        System.out.println("\n--- " + title + " ---");
        TripIterator filtered = new FilteringTripIterator(collection.createIterator(), filter);
        printFromIterator(filtered);
    }

    private void printFromIterator(TripIterator iterator) {
        int count = 0;
        while (iterator.hasNext()) {
            Trip trip = iterator.next();
            printTripDetails(trip);
            count++;
        }

        if (count == 0) {
            System.out.println("No trips found for this query");
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
