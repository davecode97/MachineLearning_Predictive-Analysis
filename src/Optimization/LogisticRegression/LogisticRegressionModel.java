package Optimization.LogisticRegression;

public class LogisticRegressionModel {
    private double n;
    private double learningRate;
    private int iterations;

    public LogisticRegressionModel() {
    }

    public LogisticRegressionModel(double n, double learningRate, int iterations) {
        this.n = n;
        this.learningRate = learningRate;
        this.iterations = iterations;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
