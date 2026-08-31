package org.example.design_patterns.Structural.Adapter.after;

public class PaymentResult {
    private final String providerName;
    private final String confirmationCode;

    public PaymentResult(String providerName, String confirmationCode) {
        this.providerName = providerName;
        this.confirmationCode = confirmationCode;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }
}

