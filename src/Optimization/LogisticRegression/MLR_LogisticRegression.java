package Optimization.LogisticRegression;

import java.math.BigDecimal;

public class MLR_LogisticRegression implements IMLR_LogisticRegression {
    BigDecimal m;
    int T, tetha;
    BigDecimal euler = BigDecimal.valueOf(Math.E);
    BigDecimal Likelihood = new BigDecimal(1);

    /**
     *  Algorithm
     *
     */
    @Override
    public void display() {

    }
}
