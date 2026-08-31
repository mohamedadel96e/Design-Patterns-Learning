package org.example.design_patterns.Behavioral.Iterator.after;

/**
 * Iterator interface that hides concrete storage details from clients.
 */
public interface TripIterator {
    boolean hasNext();

    Trip next();
}
