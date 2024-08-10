package en.artembert.proxyLogger;

public class CalculatorImpl implements CalculatorInterface {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public float sum(float a, float b) {
        return a + b;
    }

    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public float sub(float a, float b) {
        return a - b;
    }

    @Override
    public double sub(double a, double b) {
        return a - b;
    }

    @Override
    public String toString() {
        return "CalculatorImpl{}";
    }
}
