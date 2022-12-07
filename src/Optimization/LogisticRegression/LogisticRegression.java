package Optimization.LogisticRegression;

import DataSets.DataSet_LR;

public class LogisticRegression implements ILogisticRegression
{
    private double w0;
    private double w1;
    private double w2;
    private double sigmoid;


    LogisticRegressionModel logisticRegressionModel = new LogisticRegressionModel();

    DataSet_LR dataSet_lr = new DataSet_LR();


    @Override
    public void display()
    {
        logisticRegressionModel.setLearningRate(0.1);
        logisticRegressionModel.setIterations(100);
        logisticRegressionModel.setN(dataSet_lr.y.length);
        double px1 = 3.5; //prediction
        double px2 = 4.0; // prediction
        findW();
        scenario2();
        sigmoid(px1, px2);
    }


    //gradient
    private void scenario2()
    {
        double v;
        for (int j = 0; j < logisticRegressionModel.getIterations(); j++) {
            System.out.println("Iteration: " + (j + 1));
            double aux0 = 0, aux1 = 0, aux2 = 0;
            for (int i = 0; i < logisticRegressionModel.getN(); i++) {
                v = ((1 / (1 + Math.pow(Math.E, -(w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i]))))) - dataSet_lr.y[i]);

                aux0 = aux0 + v;
                aux1 = aux1 + v * dataSet_lr.x1[i];
                aux2 = aux2 + v * dataSet_lr.x2[i];
            }
            w0 = w0 - (logisticRegressionModel.getLearningRate() * (aux0));
            w1 = w1 - (logisticRegressionModel.getLearningRate() * (aux1));
            w2 = w2 - (logisticRegressionModel.getLearningRate() * (aux2));

            System.out.println("wo: " + w0 + "  |" + "  w1: " + w1 + "  |" + "  w2: " + w2);
        }
    }

    // Finding w's hat
    public  void findW()
    {
        // This step consists of initializing the weights (i.e The Model Parameters),
        // The learning rate n and # of iterations for the gradient:
        w0 = 0;
        w1 = 0;
        w2 = 0;

        double aux0 = 0, aux1 = 0, aux2 = 0;

        // Step 2
        for (int i = 0; i < logisticRegressionModel.getN(); i++)
        {
            aux0 = aux0 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]);
            aux1 = aux1 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]) * dataSet_lr.x1[i];
            aux2 = aux2 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]) * dataSet_lr.x2[i];

        }

        w0 = w0 - (logisticRegressionModel.getLearningRate() * (aux0));
        w1 = w1 - (logisticRegressionModel.getLearningRate() * (aux1));
        w2 = w2 - (logisticRegressionModel.getLearningRate() * (aux2));

        System.out.println("wo: " + w0 + "  |  " + "  w1: " + w1 + "  |  " + "  w2: " + w2);
    }

    public void sigmoid(double nx1, double nx2)
    {
        sigmoid = ((1) / (1 + Math.pow(Math.E, -(w0 + w1 * nx1 + (w2) * nx2))));
        System.out.println("Sigmoid: " + sigmoid);
        if (sigmoid > 0.5)
            System.out.println("Good!");
        else
            System.out.println("Bad!");
    }
}
