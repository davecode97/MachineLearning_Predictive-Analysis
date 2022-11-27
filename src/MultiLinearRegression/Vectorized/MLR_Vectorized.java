package MultiLinearRegression.Vectorized;

import DataSets.DataSet_Vectorized;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MLR_Vectorized {

    // Formula: beta = (X^T X)^-1 X^T y
    //---- DATASET
    DataSet_Vectorized dataSetVectorized = new DataSet_Vectorized();

    /*
      falla la inversa porque la matrix xTx esta incorrecta
     */
    public void display() {
        // --- X^T
        double[][] xT = IntStream.range(0, dataSetVectorized.X[0].length)
                .mapToObj(i -> Stream.of(dataSetVectorized.X).mapToDouble(row -> row[i]).toArray())
                .toArray(double[][]::new);
        // 17  x 1
        System.out.println(Arrays.deepToString(xT));


        //----- 1. Calculate: (X^T X)

        BigDecimal[][] xTx = multiplyMatrices(doubleToBigDecimal(xT), doubleToBigDecimal(dataSetVectorized.X));
        System.out.println(Arrays.deepToString(xTx)); // falla

        //----- 2. Calculate: (X^T y)
        BigDecimal[][] step2 = multiplyMatrices(doubleToBigDecimal(xT), doubleToBigDecimal(dataSetVectorized.y));
        System.out.println(Arrays.deepToString(step2));

        //----- 3. Calculate: (X^T X)^(-1) [INVERSE]
        BigDecimal[][] step3 = inverse(xTx);
        System.out.println(Arrays.deepToString(step3));


        //----- 4. Beta = (X^T X)^-1 * X^T y
        BigDecimal[][] step4 = multiplyMatrices(step3, step2);

        System.out.println(Arrays.deepToString(step4));
    }


    private BigDecimal[][] multiplyMatrices(BigDecimal[][] firstMatrix, BigDecimal[][] secondMatrix) {
        BigDecimal[][] result = new BigDecimal[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    private BigDecimal multiplyMatricesCell(BigDecimal[][] firstMatrix, BigDecimal[][] secondMatrix, int row, int col) {
        BigDecimal cell = new BigDecimal(0);
        for (int i = 0; i < secondMatrix.length; i++) {
            cell = cell.add(firstMatrix[row][i].multiply(secondMatrix[i][col]), MathContext.DECIMAL64);
        }

        return cell;
    }


    private static BigDecimal determinant(BigDecimal[][] matrix) {
        BigDecimal determinantBigDecimal = new BigDecimal(0);

        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (matrix.length == 2) {
            determinantBigDecimal = (matrix[0][0]).multiply(matrix[1][1]).subtract(matrix[0][1]).multiply(matrix[1][0]);
            return determinantBigDecimal;
        }

        for (int i = 0; i < matrix[0].length; i++) {
            determinantBigDecimal = determinantBigDecimal.add(BigDecimal.valueOf(Math.pow(-1, i))).multiply(matrix[0][i]).
                    multiply((determinant(minor(matrix, 0, i))));
        }

        return determinantBigDecimal;
    }

    private static BigDecimal[][] inverse(BigDecimal[][] matrix) {
        BigDecimal[][] inverse = new BigDecimal[matrix.length][matrix.length];

        // minors and cofactors
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                inverse[i][j] = BigDecimal.valueOf(Math.pow(-1, i + j)).multiply((determinant(minor(matrix, i, j))), MathContext.DECIMAL64);
            }
        }
        // adjugate and determinant
        BigDecimal aux = BigDecimal.valueOf(1);
        BigDecimal det = aux.divide(determinant(matrix), MathContext.DECIMAL64);
        BigDecimal temp;

        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                temp = inverse[i][j];
                inverse[i][j] = inverse[j][i].multiply((det), MathContext.DECIMAL64);
                inverse[j][i] = temp.multiply((det), MathContext.DECIMAL64);
            }
        }

        return inverse;
    }

    private static BigDecimal[][] minor(BigDecimal[][] matrix, int row, int column) {
        BigDecimal[][] minor = new BigDecimal[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    minor[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];

        return minor;
    }

    private BigDecimal[][] doubleToBigDecimal(double[][] array) {
        BigDecimal[][] aux = new BigDecimal[array.length][array[0].length];

        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                aux[row][col] = BigDecimal.valueOf(array[row][col]);
            }
        }

        return aux;
    }

}
