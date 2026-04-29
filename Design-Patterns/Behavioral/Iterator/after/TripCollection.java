package after;

/**
 * Aggregate interface in the Iterator pattern.
 */
public interface TripCollection {
    TripIterator createIterator();

    String getCollectionName();
}
