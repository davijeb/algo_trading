package com.lab49.algotrader.models.concurrent_change;

import com.lab49.algotrader.algos.TradingAlgorithm;
import com.lab49.algotrader.algos.TradingAlgorithmImpl;
import com.lab49.algotrader.models.PriceWindow;
import com.lab49.algotrader.models.PricingWindowMarshaller;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PriceWindowConsumer implements Runnable {

    private PriceWindow window;
    private final TradingAlgorithm tradingAlgo;

    public PriceWindowConsumer(PriceWindow window) {
        this.window = window;
        tradingAlgo = new TradingAlgorithmImpl();
    }

    @Override
    public void run() {
        if(window != null) tradingAlgo.buildTrade(window.getWindow());
    }
}