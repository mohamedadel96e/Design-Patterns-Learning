package after;

public class SendEmailCommand implements Command {
    private final EmailService emailService;
    private final String to;
    private final String subject;

    public SendEmailCommand(EmailService emailService, String to, String subject) {
        this.emailService = emailService;
        this.to = to;
        this.subject = subject;
    }

    @Override
    public void execute() {
        emailService.sendEmail(to, subject);
    }

    @Override
    public String getName() {
        return "Send email (" + to + ")";
    }
}
