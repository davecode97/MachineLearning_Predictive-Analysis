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
        logisticRegressionModel.setLearningRate(0.003);
        logisticRegressionModel.setIterations(1000);
        logisticRegressionModel.setN(dataSet_lr.y.length);

        scenario1();
        scenario2();
        renaming();

        findW(logisticRegressionModel.getLearningRate(), logisticRegressionModel.getIterations());
    }

    private void scenario1()
    {
        double m = 1.0;
        double scenario1_0 = 0;
        double scenario1_1 = 0;
        double scenario1_2 = 0;
        double v;
        for (int i = 0; i < logisticRegressionModel.getN(); i++) {
            v = ((1 / m) * (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]));
            scenario1_0 = v;
            scenario1_1 = v * dataSet_lr.x1[i];
            scenario1_2 = v * dataSet_lr.x2[i];
        }

        System.out.println("scenario1_0: " + scenario1_0);
        System.out.println("scenario1_1: " + scenario1_1);
        System.out.println("scenario1_2: " + scenario1_2);
    }

    private void scenario2()
    {
        double scenario2_0 = 0;
        double scenario2_1 = 0;
        double scenario2_2 = 0;
        double v;
        for (int i = 0; i < logisticRegressionModel.getN(); i++)
        {
            v = ((1/(1+Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))))-dataSet_lr.y[i]);

            scenario2_0 = v;
            scenario2_1 = v * dataSet_lr.x1[i];
            scenario2_2 = v * dataSet_lr.x2[i];
        }

        System.out.println("scenario2_0: " + scenario2_0);
        System.out.println("scenario2_1: " + scenario2_1);
        System.out.println("scenario2_2: " + scenario2_2);
    }

    private void renaming()
    {
        // Renaming Tetha by w (weight)
        // Where W^T*x^(i) = w_0 + w1x1 + w2x2 + ...

        double w = 0.0;
        double renaming0 = 0.0;
        double renaming1 = 0.0;
        double renaming2 = 0.0;
        double v;
        for (int i = 0; i < logisticRegressionModel.getN(); i++)
        {
            v = (1 /(1 + Math.pow(Math.E, - w)) - 1);

            renaming0 = v;
            renaming1 = v * dataSet_lr.x1[i];
            renaming2 = v * dataSet_lr.x2[i];
        }

        System.out.println("renaming0: " + renaming0);
        System.out.println("renaming1: " + renaming1);
        System.out.println("renaming2: " + renaming2);
    }

    // Finding w's hat
    private void findW(double learningRateN, int interations)
    {
        // This step consists of initializing the weights (i.e The Model Parameters),
        // The learning rate n and # of iterations for the gradient:
        w0 = 0;
        w1 = 0;
        w2 = 0;

        double aux0 = 0, aux1 = 0, aux2 = 0;

        // Step 2
        for (int i = 0; i < logisticRegressionModel.getIterations(); i++)
        {
            aux0 = aux0 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]);
            aux1 = aux1 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]) * dataSet_lr.x1[i];
            aux2 = aux2 + (1 / (1 + Math.pow(Math.E, - (w0 + (w1 * dataSet_lr.x1[i]) + (w2 * dataSet_lr.x2[i])))) - dataSet_lr.y[i]) * dataSet_lr.x2[i];

            w0 = w0 - (learningRateN * (aux0));
            w1 = w1 - (learningRateN * (aux1));
            w2 = w2 - (learningRateN * (aux2));

            System.out.println("w0: " + w0);
            System.out.println("w1: " + w1);
            System.out.println("w2: " + w2);
        }
    }

    private void sigmoid(double nx1, double nx2)
    {
        sigmoid = ((1) / (1 + Math.pow(Math.E, -(w0 + w1 * nx1 + (w2) * nx2))));
        System.out.println("Sigmoid: " + sigmoid);
        if (sigmoid > 0.5)
            System.out.println("Good!");
        else
            System.out.println("Bad!");
    }
}
