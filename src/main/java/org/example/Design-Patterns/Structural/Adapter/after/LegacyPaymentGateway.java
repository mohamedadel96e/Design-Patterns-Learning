package after;

public class LegacyPaymentGateway {
    public String charge(String accountId, int amountInCents) {
        String confirmation = "LEG-" + accountId + "-" + amountInCents;
        System.out.println("Legacy gateway charged " + amountInCents + " cents from account " + accountId + ".");
        return confirmation;
    }
}

