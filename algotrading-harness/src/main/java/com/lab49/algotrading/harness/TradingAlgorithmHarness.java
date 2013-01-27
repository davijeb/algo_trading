package com.lab49.algotrading.harness;

import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrading.harness.simulation.TradingSimulator;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Start of the application.  Standard Producer/Consumer pattern. Initially the
 * application generates the model answer given in the test sheet.  It also
 * includes a generate which can be used to generate data sets of a size defined
 * by the user. In addition it allows the user to re-run any previous test which
 * is important in testing any edge cases in a multi-threaded application.
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:24
 */
public class TradingAlgorithmHarness {

    /**
     * A single-threaded read from the queue is probably the best choice here as ordering is important.
     * In a multi-threaded read we lose all hope of contiguous sets and would then have to piece the
     * sequences back together using an internal sequence identifier on the Prices themselves. The benefits
     * of a parallel read would be lost in this case and a straight O(N) serialized approach seems to be
     * the simplest.
     */
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
	private TradingSimulator simulator;

    public static void main(String args[]) throws InterruptedException, IOException, ClassNotFoundException {

        final BlockingQueue<Price> sharedQueue = new LinkedBlockingQueue<Price>();

        EXECUTOR.execute(new PriceProducer(sharedQueue));
        EXECUTOR.execute(new PriceConsumer(sharedQueue));

        // Add the generate to allow manual publishing / replay
        new TradingSimulator(sharedQueue);
    }

}




