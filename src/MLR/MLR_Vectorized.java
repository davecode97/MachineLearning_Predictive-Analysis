package MLR;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MLR_Vectorized {
    //---- DATASET
    double[][] x = {
            {1,	41.9,	29.1 },
            {1,	43.4,	29.3 },
            {1,	43.9,	29.5 },
            {1,	44.5,	29.7 },
            {1,	47.3,	29.9 },
            {1,	47.5,	 30.3},
            {1,	47.9,	 30.5},
            {1,	50.2,	 30.7},
            {1,	52.8,	 30.8},
            {1,	53.2,	 30.9},
            {1,	56.7,	 31.5},
            {1,	  57,	 31.7},
            {1,	63.5,	 31.9},
            {1,	65.3,      32},
            {1,	71.1,	 32,1},
            {1,	  77,	 32,5},
            {1,	77.8,	 32,9},
    };
    public void MLR_Vectorized() {
        System.out.println("-----------          MLR Vectorized        ---------");

        // --- Transpose
        double[][] aux = IntStream.range(0, x[0].length)
                .mapToObj(i -> Stream.of(x).mapToDouble(row -> row[i]).toArray())
                .toArray(double[][]::new);
        //System.out.println(Arrays.deepToString(aux));


    }
}
