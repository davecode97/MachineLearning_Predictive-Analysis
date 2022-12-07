package SimpleLinearRegression;

import DataSets.DataSet_SLR;
import MultiLinearRegression.Cramer.CramerModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class SLRCramer implements ISLR
{
    DataSet_SLR dataSetSlr = new DataSet_SLR();

    double sum = 0;

    double sigmaX0       = sigma(dataSetSlr.x0_SLR);
    double sigmaX1       = sigma(dataSetSlr.x_SLR);
    double sigmaX1Square = sigmaSquare(dataSetSlr.x_SLR);
    double sigmaY        = sigma(dataSetSlr.y_SLR);
    double sigmaXY       = sigma(dataSetSlr.x_SLR, dataSetSlr.y_SLR);

    BigDecimal DS = new BigDecimal(0);
    BigDecimal D0 = new BigDecimal(0);
    BigDecimal D1 = new BigDecimal(0);
    double[] pivotMatrix = pivotMatrix();


    @Override
    public void display() {
        DS = determinant(Objects.requireNonNull(setCramerModel("SYSTEM")));
        D0 = determinant(Objects.requireNonNull(setCramerModel("BETA0")));
        D1 = determinant(Objects.requireNonNull(setCramerModel("BETA1")));

        System.out.println("y = " + D1.divide(DS, MathContext.DECIMAL64) + " x   +  " +  D0.divide(DS, MathContext.DECIMAL64) + "  + epsilon");
    }


    private CramerModel setCramerModel(String type)
    {
        CramerModel cramerModel = new CramerModel();
        if (type.matches("SYSTEM"))
        {
            cramerModel.set_pos00(sigmaX0);
            cramerModel.set_pos01(sigmaX1);

            cramerModel.set_pos10(sigmaX1);
            cramerModel.set_pos11(sigmaX1Square);

            return cramerModel;
        }
        if (type.matches("BETA0"))
        {
            cramerModel.set_pos00(pivotMatrix[0]);
            cramerModel.set_pos01(sigmaX1);

            cramerModel.set_pos10(pivotMatrix[1]);
            cramerModel.set_pos11(sigmaX1Square);

            return cramerModel;
        }
        if (type.matches("BETA1"))
        {
            cramerModel.set_pos00(sigmaX0);
            cramerModel.set_pos01(pivotMatrix[0]);

            cramerModel.set_pos10(sigmaX1);
            cramerModel.set_pos11(pivotMatrix[1]);

            return cramerModel;
        }

        return null;
    }

    private BigDecimal determinant(CramerModel cramerModel)
    {
        double[][] determinantSystem = new double[2][2];
        determinantSystem[0][0] = cramerModel.get_pos00();
        determinantSystem[0][1] = cramerModel.get_pos01();

        determinantSystem[1][0] = cramerModel.get_pos10();
        determinantSystem[1][1] = cramerModel.get_pos11();

        BigDecimal aux1BD = new BigDecimal(0);
        BigDecimal aux2BD = new BigDecimal(0);

        for (int i = 0; i < determinantSystem.length-1; i++)
            for (int j = 0; j < determinantSystem[0].length-1 ; j++)
                aux1BD = aux1BD.add(BigDecimal.valueOf(determinantSystem[i][j] * determinantSystem[i + 1][j + 1]));

        for (int k = 0; k < determinantSystem.length-1; k++)
            for (int m = determinantSystem[0].length-1; m > 0; m--)
                    aux2BD = aux2BD.add(BigDecimal.valueOf(determinantSystem[k][m] * determinantSystem[k + 1][m - 1]));


        return aux1BD.subtract(aux2BD, MathContext.DECIMAL64);
    }

    private double[] pivotMatrix()
    {
        double beta1 = sigmaY;
        double beta2 = sigmaXY;

        pivotMatrix = new double[2];
        pivotMatrix[0] = beta1;
        pivotMatrix[1] = beta2;

        return pivotMatrix;
    }

    private double sigma(int [] array)
    {
        sum = 0.0;
        for (double i : array)
            sum += i;

        return sum;
    }

    private double sigmaSquare(int [] array)
    {
        sum = 0.0;
        for (double i : array)
            sum += Math.pow(i, 2);

        return sum;
    }

    private double sigma(int[] array1, int[] array2)
    {
        sum = 0;
        double[] auxArray = new double[array1.length];
        for (int row = 0; row < array1.length; row++)
        {
            auxArray[row] = array1[row] * array2[row];
            sum += auxArray[row];
        }

        return sum;
    }


}

