package MLR.Cramer;

import MLR.Cramer.CramerModel;

import java.util.Arrays;

public class MLR_Cramer implements IMLR_Cramer{

        DataSet dataSet = new DataSet();
        double sum = 0;

        double _SigmaX0i       = Sigma(dataSet.X0);                // 1. SIGMA X0, i
        double _SigmaX1i       = Sigma(dataSet.X1);                // 2. SIGMA X1, i
        double _SigmaX2i       = Sigma(dataSet.X2);                // 3. SIGMA X2, i
        double _SigmaYi        = Sigma(dataSet.Y);                 // 4. SIGMA Y, i
        double _SigmaX1Squarei = SigmaSquare(dataSet.X1);          // 5. SIGMA X1^2, i
        double _SigmaX2Squarei = SigmaSquare(dataSet.X2);           // 6. SIGMA X2^2, i
        double _SigmaX1iYi     = Sigma(dataSet.X1, dataSet.Y);      // 7. SIGMA X1,i y
        double _SigmaX2iYi     = Sigma(dataSet.X2, dataSet.Y);      // 8. SIGMA X2,i y
        double _SigmaX1iX2i    = Sigma(dataSet.X1, dataSet.X2);     // 9. SIGMA X1,i X2, i

        double[][] _pivotMatrix = pivotMatrix();


        @Override
        public void display()
        {
//               pivotMatrix();
                CramerModel cramerModelDs = new CramerModel();
                cramerModelDs.set_pos00(_SigmaX0i);
                cramerModelDs.set_pos01(_SigmaX1i);
                cramerModelDs.set_pos02(_SigmaX2i);
                cramerModelDs.set_pos03(_SigmaX0i);
                cramerModelDs.set_pos04(_SigmaX1i);

                cramerModelDs.set_pos10(_SigmaX1i);
                cramerModelDs.set_pos11(_SigmaX1Squarei);
                cramerModelDs.set_pos12(_SigmaX1iX2i);
                cramerModelDs.set_pos13(_SigmaX1i);
                cramerModelDs.set_pos14(_SigmaX1Squarei);

                cramerModelDs.set_pos20(_SigmaX2i);
                cramerModelDs.set_pos21(_SigmaX1iX2i);
                cramerModelDs.set_pos22(_SigmaX2Squarei);
                cramerModelDs.set_pos23(_SigmaX2i);
                cramerModelDs.set_pos24(_SigmaX1iX2i);
                Determinat(cramerModelDs);

        }

        private double[][] Determinat(CramerModel cramerModel)
        {
                double[][] _determinantSystem = new double[3][5];
                _determinantSystem[0][0] = cramerModel.get_pos00();
                _determinantSystem[0][1] = cramerModel.get_pos01();
                _determinantSystem[0][2] = cramerModel.get_pos02();
                _determinantSystem[0][3] = cramerModel.get_pos03();
                _determinantSystem[0][4] = cramerModel.get_pos04();

                _determinantSystem[1][0] = cramerModel.get_pos10();
                _determinantSystem[1][1] = cramerModel.get_pos11();
                _determinantSystem[1][2] = cramerModel.get_pos12();
                _determinantSystem[1][3] = cramerModel.get_pos13();
                _determinantSystem[1][4] = cramerModel.get_pos14();

                _determinantSystem[2][0] = cramerModel.get_pos20();
                _determinantSystem[2][1] = cramerModel.get_pos21();
                _determinantSystem[2][2] = cramerModel.get_pos22();
                _determinantSystem[2][3] = cramerModel.get_pos23();
                _determinantSystem[2][4] = cramerModel.get_pos24();

                System.out.println(Arrays.deepToString(_determinantSystem));
                // hacer multiplicaciion con for
                double aux1 = 0;
                double aux2 = 0;
                for (int i = 0; i <_determinantSystem.length-2; i++)
                {
                        for (int j = 0; j < _determinantSystem[0].length-2; j++)
                        {
                               aux1 = _determinantSystem[i][j] *
                                       _determinantSystem[i+1][j+1] * _determinantSystem[i+2][j+2];
                        }
                }

                for (int k = 0; k < _determinantSystem.length-2; k++)
                {
                        for (int m = _determinantSystem[0].length-2; m > 0; m--)
                        {
                                System.out.println(_determinantSystem[k][m]);
                                System.out.println(_determinantSystem[k + 1][m - 1]);

                                System.out.println(_determinantSystem[k + 2][m - 2]);
                                aux2 = _determinantSystem[k][m] *
                                        _determinantSystem[k + 1][m - 1] * _determinantSystem[k + 2][m - 2];

                        }
                }

                System.out.println(aux1-aux2);
                return  _determinantSystem;

        }

        private double[][] pivotMatrix()
        {
                double betha1 = _SigmaYi;
                double betha2 = _SigmaX1iYi;
                double betha3 = _SigmaX2iYi;

                double[][] _pivotMatrix = new double [1][3];
                _pivotMatrix[0][0] = betha1;
                _pivotMatrix[0][1] = betha2;
                _pivotMatrix[0][2] = betha3;

                System.out.println(Arrays.deepToString(_pivotMatrix));
                return _pivotMatrix;
        }

        private  double Sigma(double[][] array)
        {

                // sum
                for (double[] i : array)
                        for (double num : i)
                                sum+=num;

//                System.out.println("---------  1. SIGMA X0, i  ---------");
                System.out.println(sum);
                System.out.println();

                return sum;
        }

        private double Sigma(double[][] X1, double[][] Y)
        {
                double sum = 0;
                double[][] auxArray= new double[X1.length][X1[0].length];

                for (int row = 0; row < X1.length; row++)
                {
                        for (int col = 0; col < X1[row].length; col++)
                        {
                                auxArray[row][col] = X1[row][col] * Y[row][col];
                                sum  += auxArray[row][col];
                        }
                }
//                System.out.println("---------  7. SIGMA X1,i y  ---------");
                System.out.println(sum);
                System.out.println();

                return sum;
        }

        // CHECK ITERATE VALUES
        private  double SigmaSquare(double[][] array)
        {
                // sum
                for (double[] i : array)
                        for (double num : i)
                                sum+=Math.pow(num, 2);

//                System.out.println("---------  1. SIGMA X0, i  ---------");
                System.out.println(sum);
                System.out.println();

                return sum;
        }

}
