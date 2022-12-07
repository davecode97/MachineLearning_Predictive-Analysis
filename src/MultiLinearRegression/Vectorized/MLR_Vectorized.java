package MultiLinearRegression.Vectorized;

import DataSets.DataSet_Vectorized;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MLR_Vectorized {

    // Formula: beta = (X^T X)^-1 X^T y
    //---- DATASET
    DataSet_Vectorized dataSetVectorized = new DataSet_Vectorized();

    public void display() {
        // --- X^T
        double[][] xT = transposeMatrix(dataSetVectorized.X);
        // 17  x 1
        System.out.println("0. X^T [Transpose]:  " + Arrays.deepToString(xT));

        //----- 1. Calculate: (X^T X)
        double[][] xTx = multiplyMatrices(xT, dataSetVectorized.X);
        System.out.println("1. Calculate: (X^T X):  " + Arrays.deepToString(xTx));

        //----- 2. Calculate: (X^T y)
        double[][] step2 = multiplyMatrices(xT, dataSetVectorized.y);
        System.out.println("2. Calculate: (X^T y):  " + Arrays.deepToString(step2));

        //----- 3. Calculate: (X^T X)^(-1) [INVERSE]
        double[][] step3 = inverse(xTx);
        System.out.println("3. Calculate: (X^T X)^(-1) [INVERSE]:  " + Arrays.deepToString(step3));


        //----- 4. Beta = (X^T X)^-1 * X^T y
        double[][] step4 = multiplyMatrices(step3, step2);
        System.out.println("4. Beta = (X^T X)^-1 * X^T y:  " + Arrays.deepToString(step4));

        for (int i = 0; i < step4.length; i++)
            System.out.println("Beta"+i+": "+step4[i][0]);

        System.out.println("\ny = " + step4[0][0] +"  +  "+ step4[1][0] +" x1  +  "+ step4[2][0] +" x2   + epsilon");
    }


    private double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                double cell = 0;
                for (int i = 0; i < secondMatrix.length; i++) {
                    cell += firstMatrix[row][i] * secondMatrix[i][col];
                    result[row][col] = cell;
                }
            }
        }

        return result;
    }

    private double[][] inverse(double[][] z) {
        double[][] X = transferMatrix(z);
        double[][] I = new double[X.length][X[0].length];
        double A, B, C, D;
        for(int i = 0; i < X.length; i++){
            I[i][i] = 1.0;
        }
        for(int i = 1; i < X.length; i++){
            for(int j = 0; j < i; j++){
                A = X[i][j];
                B = X[j][j];
                for(int k = 0; k < X.length; k++){
                    X[i][k] = X[i][k] - (A/B)*X[j][k];
                    I[i][k] = I[i][k] - (A/B)*I[j][k];
                }
            }
        }
        for(int i = 1; i < X.length; i++){
            for(int j = 0; j < i; j++){
                C = X[j][i];
                D = X[i][i];

                for(int k = 0; k < X.length; k++){
                    X[j][k] = X[j][k] - (C/D)*X[i][k];
                    I[j][k] = I[j][k] - (C/D)*I[i][k];
                }
            }
        }
        for(int i = 0; i < X.length; i++){
            for(int j = 0; j < X[0].length; j++){
                I[i][j] = I[i][j] / X[i][i];
            }
        }
        return I;
    }

    private double[][] transferMatrix(double[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        double[][] T = new double[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                T[i][j] = matrix[i][j];
            }
        }
        return T;
    }

    private double[][] transposeMatrix(double [][] matrix) {
        return IntStream.range(0, matrix[0].length)
                .mapToObj(i -> Stream.of(matrix).mapToDouble(row -> row[i]).toArray())
                .toArray(double[][]::new);
    }
}
