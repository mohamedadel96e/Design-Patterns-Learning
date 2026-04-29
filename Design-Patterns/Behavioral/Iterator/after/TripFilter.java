package after;

/**
 * Small abstraction that lets us compose additional traversal behavior.
 */
public interface TripFilter {
    boolean matches(Trip trip);
}
