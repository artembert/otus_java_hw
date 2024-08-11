package en.artembert.proxyLogger;

import en.artembert.proxyLogger.annotation.Log;

public class CalculatorImpl implements CalculatorLogInterface {
    @Log
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Log
    @Override
    public float sum(float a, float b) {
        return a + b;
    }

    @Log
    @Override
    public double sum(double a, double b) {
        return a + b;
    }

    @Log
    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Log
    @Override
    public float sub(float a, float b) {
        return a - b;
    }

    @Log
    @Override
    public double sub(double a, double b) {
        return a - b;
    }

    @Override
    public String toString() {
        return "CalculatorImpl{}";
    }
}
