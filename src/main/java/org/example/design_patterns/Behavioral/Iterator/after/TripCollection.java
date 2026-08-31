package org.example.design_patterns.Behavioral.Iterator.after;

/**
 * Aggregate interface in the Iterator pattern.
 */
public interface TripCollection {
    TripIterator createIterator();

    String getCollectionName();
}
