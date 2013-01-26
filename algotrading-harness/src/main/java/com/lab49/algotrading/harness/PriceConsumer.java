package com.lab49.algotrading.harness;

import com.lab49.algotrader.models.Price;
import com.lab49.algotrader.models.PricingWindowMarshaller;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PriceConsumer implements Runnable {

    PricingWindowMarshaller ONLY_ONE = new PricingWindowMarshaller();

    private final BlockingQueue<Price> sharedQueue;

    public PriceConsumer(BlockingQueue<Price> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {

            try {
                ONLY_ONE.add(sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(PriceConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}