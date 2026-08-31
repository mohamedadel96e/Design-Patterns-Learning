package org.example.design_patterns.Behavioral.Strategy.after;

import java.util.Date;

/**
 * Represents a payment request from a customer
 * Same as before version - data structure doesn't change
 */
public class PaymentRequest {
    private String paymentMethod;
    private double amount;
    private String currency;
    
    // Payment method specific data
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;
    
    private String paypalEmail;
    private String paypalPassword;
    
    private String walletAddress;
    private String cryptoType;
    
    private String accountNumber;
    private String routingNumber;
    private String bankName;
    
    private String customerEmail;
    private String description;

    public PaymentRequest(String paymentMethod, double amount, String currency) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.currency = currency;
    }

    // Getters and setters
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }

    public String getPaypalEmail() { return paypalEmail; }
    public void setPaypalEmail(String paypalEmail) { this.paypalEmail = paypalEmail; }

    public String getPaypalPassword() { return paypalPassword; }
    public void setPaypalPassword(String paypalPassword) { this.paypalPassword = paypalPassword; }

    public String getWalletAddress() { return walletAddress; }
    public void setWalletAddress(String walletAddress) { this.walletAddress = walletAddress; }

    public String getCryptoType() { return cryptoType; }
    public void setCryptoType(String cryptoType) { this.cryptoType = cryptoType; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getRoutingNumber() { return routingNumber; }
    public void setRoutingNumber(String routingNumber) { this.routingNumber = routingNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
