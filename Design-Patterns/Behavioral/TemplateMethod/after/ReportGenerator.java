/**
 * Abstract base class that defines the template method for report generation.
 * The template method defines the skeleton of the algorithm, and subclasses
 * provide specific implementations for certain steps.
 */
public abstract class ReportGenerator {
    protected String title;
    protected String[] data;

    public ReportGenerator(String title, String[] data) {
        this.title = title;
        this.data = data;
    }

    /**
     * Template Method - Defines the skeleton of the report generation algorithm.
     * This method is final to prevent subclasses from changing the algorithm structure.
     */
    public final void generateReport() {
        // Step 1: Initialize (implemented by subclasses)
        initialize();

        // Step 2: Add header (implemented by subclasses)
        addHeader();

        // Step 3: Process and format data (implemented by subclasses)
        formatContent();

        // Step 4: Add footer (implemented by subclasses)
        addFooter();

        // Step 5: Hook method - optional customization point
        if (shouldAddWatermark()) {
            addWatermark();
        }

        // Step 6: Finalize (implemented by subclasses)
        finalize();

        // Common finalization step for all reports
        printSuccess();
    }

    /**
     * Abstract methods - Must be implemented by subclasses
     */
    protected abstract void initialize();
    protected abstract void addHeader();
    protected abstract void formatContent();
    protected abstract void addFooter();
    protected abstract void finalize();
    protected abstract String getReportType();

    /**
     * Hook method - Provides a default implementation that can be overridden.
     * By default, watermark is not added.
     */
    protected boolean shouldAddWatermark() {
        return false;
    }

    /**
     * Hook method - Optional customization point with default implementation.
     */
    protected void addWatermark() {
        System.out.println("Adding default watermark: CONFIDENTIAL");
    }

    /**
     * Common method used by all subclasses
     */
    private void printSuccess() {
        System.out.println(getReportType() + " Report generated successfully!");
        System.out.println("────────────────────────────────\n");
    }
}
