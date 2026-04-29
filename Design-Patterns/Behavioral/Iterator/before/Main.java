package before;

/**
 * Demonstrates the pain points before applying Iterator pattern.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" TRAVEL ITINERARY - BEFORE ITERATOR PATTERN");
        System.out.println("============================================================");

        BookedTrips bookedTrips = new BookedTrips();
        WishlistTrips wishlistTrips = new WishlistTrips();
        seedTrips(bookedTrips, wishlistTrips);

        ItineraryService service = new ItineraryService();

        service.printBookedTrips(bookedTrips);
        service.printWishlistTrips(wishlistTrips);
        service.printBudgetFriendlyTrips(bookedTrips, wishlistTrips);
        service.printShortTrips(bookedTrips, wishlistTrips, 4);

        System.out.println("\nProblems in this design:");
        System.out.println("1) Service knows whether data is array or list");
        System.out.println("2) Traversal loops are duplicated in every operation");
        System.out.println("3) Internal collection details leak outside collection classes");
        System.out.println("4) New collection type forces service changes");
    }

    private static void seedTrips(BookedTrips bookedTrips, WishlistTrips wishlistTrips) {
        bookedTrips.addTrip(new Trip("B-101", "Cairo", 3, 220.00, true));
        bookedTrips.addTrip(new Trip("B-102", "Istanbul", 6, 780.00, false));
        bookedTrips.addTrip(new Trip("B-103", "Athens", 4, 460.00, true));
        bookedTrips.addTrip(new Trip("B-104", "Zurich", 5, 1200.00, false));

        wishlistTrips.addTrip(new Trip("W-201", "Amman", 2, 180.00, true));
        wishlistTrips.addTrip(new Trip("W-202", "Tokyo", 8, 2100.00, false));
        wishlistTrips.addTrip(new Trip("W-203", "Lisbon", 4, 540.00, true));
        wishlistTrips.addTrip(new Trip("W-204", "Reykjavik", 5, 980.00, false));
    }
}
