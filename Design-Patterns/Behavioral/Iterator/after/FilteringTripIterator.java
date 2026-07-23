package after;

import java.util.NoSuchElementException;

/**
 * Decorator-style iterator that wraps another iterator and yields only matching trips.
 */
public class FilteringTripIterator implements TripIterator {
    private final TripIterator delegate;
    private final TripFilter filter;

    private Trip nextMatch;
    private boolean prepared;

    public FilteringTripIterator(TripIterator delegate, TripFilter filter) {
        this.delegate = delegate;
        this.filter = filter;
        this.prepared = false;
        this.nextMatch = null;
    }

    @Override
    public boolean hasNext() {
        prepareNextIfNeeded();
        return nextMatch != null;
    }

    @Override
    public Trip next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No trip matches the filter");
        }

        Trip current = nextMatch;
        nextMatch = null;
        prepared = false;
        return current;
    }

    private void prepareNextIfNeeded() {
        if (prepared) {
            return;
        }

        while (delegate.hasNext()) {
            Trip candidate = delegate.next();
            if (filter.matches(candidate)) {
                nextMatch = candidate;
                prepared = true;
                return;
            }
        }

        nextMatch = null;
        prepared = true;
    }
}
