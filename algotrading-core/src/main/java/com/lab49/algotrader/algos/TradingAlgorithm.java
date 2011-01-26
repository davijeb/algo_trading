package com.lab49.algotrader.algos;

import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.models.trade.Trade;

import java.util.List;

/**
 * The core TradingAlgorithm interface
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public interface TradingAlgorithm {

    /**
     * Builds a trade to be executed based on the supplied price.
	 *
     * @param price data
     * @return trade to execute
     */
    Trade buildTrade(Price price);

    /**
     * Builds a trade to be executed based on the supplied prices.
	 *
     * @param prices data
     * @return trade to execute
     */
    Trade buildTrade(List<Price> prices);

    /**
     * Get the allowed product names to be traded.
	 *
     * @return the product name String array
     */
    String[] getProductNames();

    /**
     * Does the price contain a product name which forms
     * part of the allowed values?
     * @param price the price we wish to validate
     * @return true if it contains a valid product name
     */
	boolean isValidProductName(Price price);
}
