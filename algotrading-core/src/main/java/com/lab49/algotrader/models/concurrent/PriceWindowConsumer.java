package com.lab49.algotrader.models.concurrent;

import com.lab49.algotrader.algos.TradingAlgorithm;
import com.lab49.algotrader.algos.TradingAlgorithmImpl;
import com.lab49.algotrader.models.price.PriceWindow;

/**
 * Runnable which executes trade generation based on a PriceWindow.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PriceWindowConsumer implements Runnable {

    private final PriceWindow window;
    private static final TradingAlgorithm tradingAlgo = new TradingAlgorithmImpl();

	/**
	 * Constructor given a cloned price window.
	 * @param window the pricing window
	 */
    public PriceWindowConsumer(final PriceWindow window) {
        this.window = window;
    }

    @Override
    public void run() {
        if(window != null) tradingAlgo.buildTrade(window.getWindow());
    }
}