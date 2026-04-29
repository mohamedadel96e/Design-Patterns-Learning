package after;

public class BudgetFriendlyFilter implements TripFilter {
    @Override
    public boolean matches(Trip trip) {
        return trip.isBudgetFriendly();
    }
}
