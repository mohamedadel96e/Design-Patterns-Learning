package org.example.design_patterns.Behavioral.Command.after;

public class EmailService {
    public void sendEmail(String to, String subject) {
        System.out.println("Sending email to " + to + " with subject: " + subject);
    }
}
