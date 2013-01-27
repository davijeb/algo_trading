package com.lab49.algotrader.models.concurrent;

import com.lab49.algotrader.algos.TradingAlgorithm;
import com.lab49.algotrader.algos.TradingAlgorithmImpl;
import com.lab49.algotrader.models.price.PriceWindow;

/**
 * TODO: finish
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:21
 */
public class PriceWindowConsumer implements Runnable {

    private PriceWindow window;
    private static final TradingAlgorithm tradingAlgo = new TradingAlgorithmImpl();

    public PriceWindowConsumer(PriceWindow window) {
        this.window = window;
    }

    @Override
    public void run() {
        if(window != null) tradingAlgo.buildTrade(window.getWindow());
    }
}