package before;

public class JobRequest {
    private final JobType type;
    private final String primary;
    private final String secondary;

    public JobRequest(JobType type, String primary, String secondary) {
        this.type = type;
        this.primary = primary;
        this.secondary = secondary;
    }

    public JobType getType() {
        return type;
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return secondary;
    }
}
