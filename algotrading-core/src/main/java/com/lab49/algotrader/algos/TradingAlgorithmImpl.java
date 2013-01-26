package com.lab49.algotrader.algos;

import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.calcs.Calculator;
import com.lab49.algotrader.calcs.LinearRegressionCalculator;
import com.lab49.algotrader.enums.DirectionEnum;
import com.lab49.algotrader.models.Price;
import com.lab49.algotrader.models.Trade;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the TradingAlgorithm. Given a Price
 * or list of Prices it is able to build a single Trade.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class TradingAlgorithmImpl implements TradingAlgorithm {

	// The calculator we wish to use to check for upward trending data
    private final Calculator calculator = new LinearRegressionCalculator();

    // Product names to be traded
    private static String[] productNames;

	/**
	 * Constructor which sets the valid product names from a property file
	 */
    public TradingAlgorithmImpl() {
        this.productNames = TradingProperties.INSTANCE.getStringArray("product.names");
    }

	/**
	 * Given a price we can construct a trade.
	 *
	 * @param price the price data we wish to trade with
	 * @return a single trade
	 */
    @Override
    public Trade buildTrade(final Price price) {

        if(price == null) return null;

        if(!isValidProductName(price)) {
			// should never get here...
            throw new RuntimeException("Unable to build trade with unknown product name: " + price.getProductName());
		}

        if(price != null) {
            Trade t =  Trade.create(price,DirectionEnum.BUY);
            System.err.println(t);
            return t;
        }

        Logger.getLogger(TradingAlgorithmImpl.class.getName()).log(Level.WARNING, "No trade generated for " + price);
        return null;
    }

	/**
	 * Given a list of prices we ask the calculator to return the
	 * upward trending last price (if any).
	 *
	 * @param prices the price window
	 * @return a single trade
	 */
    @Override
    public Trade buildTrade(final List<Price> prices) {
        return buildTrade(calculator.calc(prices));
    }

    @Override
    public final String[] getProductNames() {
        return productNames;
    }

    /**
     * Check to see if the <code>Price</code> has a valid
     * product name.
	 *
     * @param price the price we wish to check
     * @return true if it has a valid product name
     */
	@Override
    public boolean isValidProductName(final Price price) {
        for(int i=0; i < productNames.length; i++) {
            if(productNames[i].equals(price.getProductName())) return true;
        }
		System.err.println("Unrecognised product name: " + price.getProductName());
		return false;
    }
}
