package Optimization.DescendingGradient;

import DataSets.DataSet_DG;

import java.math.BigDecimal;
import java.math.MathContext;

public class DescendingGradient implements IDescendingGradient {
    DataSet_DG dataSet = new DataSet_DG();
    double x1Sigma = sigma(dataSet.x1);
    int n = dataSet.x1.length;

    //---- STEP 1: Setting up parameters
    BigDecimal Beta0 = new BigDecimal(0);
    BigDecimal Beta1 = new BigDecimal(0);

    //----- Learning Rate
    BigDecimal Alfa  = BigDecimal.valueOf(0.003);
    BigDecimal tolerance;

    @Override
    public void display()
    {
        tolerance = new BigDecimal(0.1);
        //---- STEP 2: InverseDelta f(Beta0, Beta1)
        BigDecimal step2BigDecimalB1 = objectiveFunction("BETA1");
        BigDecimal step2BigDecimalB0 = objectiveFunction("BETA0");
        BigDecimal auxBeta0 = Beta0;
        BigDecimal auxBeta1 = Beta1;
        System.out.println("Epsilon " + calculateEpsilon().doubleValue());
        //---- STEP 3: CALCULATE Epsilon
        if (calculateEpsilon().doubleValue() < 0.4 && calculateEpsilon().doubleValue() > -0.4) {
            System.out.println("BETA0: " + Beta0 + "  " + "BETA1: " + Beta1);
            System.out.println("Error close to 0, thus program conclude...");

        } else {
        //---- STEP 4: LEARNING RULES
            // CHECK DYNAMIC PROGRAMMING (MEMORIZATION SPENT)
            // Global scope memorization or passing arguments through method
            Beta0 = learningRulesBeta(step2BigDecimalB0, auxBeta0);
            Beta1 = learningRulesBeta(step2BigDecimalB1, auxBeta1);
            System.out.println("BETA0: " + Beta0 + "  " + "BETA1: " + Beta1);
            System.out.println("> Error isn't close to 0, thus program will back to step 3");

            //----- BACK TO STEP 3
            display();
        }
    }

    private BigDecimal objectiveFunction(String beta)
    {
        BigDecimal step2 = new BigDecimal(-2);
        BigDecimal step2SigmaBeta = new BigDecimal(0);

        step2SigmaBeta = step2SigmaBeta.add(beta.matches("BETA1") ?
                sigmaObjectiveFunction(dataSet.y, dataSet.x1,  "BETA1") : sigmaObjectiveFunction(dataSet.y, dataSet.x1,  "BETA0"));

        return step2.divide(BigDecimal.valueOf(n), MathContext.DECIMAL64).multiply(step2SigmaBeta);
    }

    private BigDecimal calculateEpsilon()
    {
        BigDecimal step3 = new BigDecimal(1);

        return step3.divide(BigDecimal.valueOf(n), MathContext.DECIMAL64).multiply(sigma2(dataSet.y, dataSet.x1));
    }

    private BigDecimal learningRulesBeta(BigDecimal objectiveFunctionB0, BigDecimal currentBeta)
    {
        return currentBeta.subtract(Alfa.multiply(objectiveFunctionB0));

    }

    private  double sigma(double[][] array)
    {
        double sum = 0.0;
        for (double[] i : array)
            for (double num : i)
                sum+=num;

        return sum;
    }

    private BigDecimal sigmaObjectiveFunction(double[][] yi, double[][] xi, String beta)
    {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal[][] auxArray = new BigDecimal[yi.length][xi[0].length];

        for (int row = 0; row < xi.length; row++)
        {
            for (int col = 0; col < xi[row].length; col++)
            {
                if (beta.matches("BETA1"))
                {
                    auxArray[row][col] = BigDecimal.valueOf(x1Sigma).multiply(BigDecimal.valueOf(yi[row][col]).
                            subtract((Beta0.add((Beta1.multiply(BigDecimal.valueOf(xi[row][col])))))));
                } else {
                    auxArray[row][col] = BigDecimal.valueOf(yi[row][col]).
                            subtract((Beta0.add((Beta1.multiply(BigDecimal.valueOf(xi[row][col]))))));
                }
                sum = sum.add(auxArray[row][col]);

            }
        }

        return sum;
    }

    private BigDecimal sigma2(double[][] yi, double[][] xi)
    {
        BigDecimal sum = new BigDecimal(0);
        BigDecimal[][] auxArray = new BigDecimal[yi.length][xi[0].length];

        for (int row = 0; row < xi.length; row++)
        {
            for (int col = 0; col < xi[row].length; col++)
            {
                auxArray[row][col] = BigDecimal.valueOf(yi[row][col]).
                        subtract((Beta0.add((Beta1.multiply(BigDecimal.valueOf(xi[row][col])))))).pow(2,MathContext.DECIMAL64);
                sum = sum.add(auxArray[row][col]);
            }
        }

        return sum;
    }
}
