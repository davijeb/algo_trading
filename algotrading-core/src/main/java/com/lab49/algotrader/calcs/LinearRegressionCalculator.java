package com.lab49.algotrader.calcs;

import com.lab49.algotrader.utils.annotations.ThreadSafe;
import com.lab49.algotrader.models.price.Price;
import org.apache.commons.math.stat.regression.SimpleRegression;

import java.util.List;

/**
 * The LinearRegressionCalculator uses a more complex method to
 * determine if there is an upward trend in the price data. Using
 * the apache commons maths libs it is able to generate a function
 * of the form:
 *
 * 	y = mx + c
 *
 * 	To signify an upward trend we require m (the slope) to be > 0
 *
 * 	@author Jeremy Davies [jerdavies@gmail.com]
 */
@ThreadSafe
public class LinearRegressionCalculator implements Calculator {

   /**
    * Uses apache maths linear regression to calculate slope (analogous to trend)
    */
    private final SimpleRegression linearRegression = new SimpleRegression();

	/**
	 * Calculate the slope of the price points and checks it is > 0 (which
	 * signifies an upward trend).
	 *
	 * @param prices the price window
	 * @return the price
	 */
    @Override
    public Price calc(List<Price> prices) {

        check(prices);

        // Need to obtain the mutex lock on the linear regression object
        // as the SimpleRegression class is probably not thread-safe. Lock-splitting
        // in this manner reduces actualized lock duration.
        synchronized (linearRegression) {

            linearRegression.clear();

            for(int i=0; i < prices.size(); i++) {
               linearRegression.addData(i, prices.get(i).getPrice());
            }
            // +'ve slope == upward trend
            if (linearRegression.getSlope() > 0) {
                return prices.get(prices.size()-1);
			}
        }

        return null;
    }

	/**
	 * TODO This should be moved to an abstract parent class
	 * @param prices the prices we wish to validate
	 */
    private void check(List<Price> prices) {
        if(prices == null)    throw new RuntimeException("Unable to calculate moving average on null input");
        if(prices.size() > 4) throw new RuntimeException("Unable to calculate moving average input size > 4");
    }
}

