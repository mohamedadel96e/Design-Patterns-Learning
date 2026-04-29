package after;

/**
 * Domain object used in the Iterator pattern example.
 */
public class Trip {
    private final String id;
    private final String destination;
    private final int durationDays;
    private final double estimatedCost;
    private final boolean budgetFriendly;

    public Trip(String id, String destination, int durationDays, double estimatedCost, boolean budgetFriendly) {
        this.id = id;
        this.destination = destination;
        this.durationDays = durationDays;
        this.estimatedCost = estimatedCost;
        this.budgetFriendly = budgetFriendly;
    }

    public String getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public boolean isBudgetFriendly() {
        return budgetFriendly;
    }

    @Override
    public String toString() {
        return String.format("Trip{id='%s', destination='%s', days=%d, cost=%.2f, budgetFriendly=%s}",
                id, destination, durationDays, estimatedCost, budgetFriendly);
    }
}
