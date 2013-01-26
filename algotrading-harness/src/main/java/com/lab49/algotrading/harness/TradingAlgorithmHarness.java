package com.lab49.algotrading.harness;

import com.lab49.algotrader.models.Price;
import com.lab49.algotrader.models.concurrent_change.PriceWindowConsumer;
import com.lab49.algotrading.harness.simulation.TradingSimulator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: finish
 *
 * Logger.getLogger(PriceWindowConsumer.class.getName()).log(Level.SEVERE, "Hi there");
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class TradingAlgorithmHarness {

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static TradingSimulator simulator;

    public static void main(String args[]) throws InterruptedException, IOException, ClassNotFoundException {

        final BlockingQueue<Price> sharedQueue = new LinkedBlockingQueue<Price>();

        EXECUTOR.execute(new PriceProducer(sharedQueue));
        EXECUTOR.execute(new PriceConsumer(sharedQueue));

        simulator = new TradingSimulator(sharedQueue);
    }

}




