package en.artembert.testRunner;

public class TestResult {
    private int successful;
    private int failed;

    public void incrementSuccessful() {
        successful++;
    }

    public void incrementFailed() {
        failed++;
    }

    public int getSuccessful() {
        return successful;
    }

    public int getFailed() {
        return failed;
    }

    public int getTotal() {
        return successful + failed;
    }
}
