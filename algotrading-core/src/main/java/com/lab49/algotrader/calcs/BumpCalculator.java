package com.lab49.algotrader.calcs;

import com.lab49.algotrader.utils.annotations.ThreadSafe;
import com.lab49.algotrader.models.price.Price;

import java.util.List;

/**
 * The BumpCalculator looks at both the scale and direction of a point p(1) and
 * compares it to a point p(0). For all point we end up with the magnitude of UP
 * vs DOWN movements.  IFF UP movements > DOWN movements then we have an upward
 * trend  This class is thread safe as there is no shared access to mutable state
 * variables.  I made up the name BumpCalculator as it looks at the successive
 * bumps for each price point.
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:20
 */
@ThreadSafe
public class BumpCalculator implements Calculator {

	/**
	 * Calculate the scale and direction of a point
	 * p(1) and compares it to a point p(0). For all point we end up with
	 * the magnitude of UP vs DOWN movements.
	 *
	 * IFF UP movements > DOWN movements then we have an upward trend
	 *
	 * @param prices the price window
	 * @return the last price if an upward trend was detected
	 */
    @Override
    public Price calc(final List<Price> prices) {

        check(prices);

        double down = 0;
        double up   = 0;

        for(int i=1; i < prices.size(); i++) {
            if(prices.get(i).getPrice() > prices.get(i-1).getPrice())
                up+=prices.get(i).getPrice() - prices.get(i-1).getPrice();
            if(prices.get(i).getPrice() < prices.get(i-1).getPrice())
                down+=prices.get(i-1).getPrice() - prices.get(i).getPrice();
        }
        if(up > down) return prices.get(prices.size()-1);

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
