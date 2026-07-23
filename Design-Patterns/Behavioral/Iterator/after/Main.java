package after;

/**
 * Demonstrates the Iterator pattern with a travel itinerary use case.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" TRAVEL ITINERARY - WITH ITERATOR PATTERN");
        System.out.println("============================================================");

        BookedTrips bookedTrips = new BookedTrips();
        WishlistTrips wishlistTrips = new WishlistTrips();
        seedTrips(bookedTrips, wishlistTrips);

        ItineraryService service = new ItineraryService();

        // Same API for different underlying data structures.
        service.printTrips(bookedTrips);
        service.printTrips(wishlistTrips);

        // Runtime composition: plug any filter without changing the collections.
        service.printFilteredTrips(bookedTrips, new BudgetFriendlyFilter(), "Budget-friendly Booked Trips");
        service.printFilteredTrips(wishlistTrips, new BudgetFriendlyFilter(), "Budget-friendly Wishlist Trips");

        service.printFilteredTrips(bookedTrips, new ShortTripFilter(4), "Short Booked Trips <= 4 days");
        service.printFilteredTrips(wishlistTrips, new ShortTripFilter(4), "Short Wishlist Trips <= 4 days");

        System.out.println("\nBenefits in this design:");
        System.out.println("1) ItineraryService no longer depends on array/list traversal details");
        System.out.println("2) Traversal logic is centralized in iterator classes");
        System.out.println("3) New collection types can join by creating an iterator");
        System.out.println("4) New views can be added with iterator wrappers, not with duplicated loops");
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
