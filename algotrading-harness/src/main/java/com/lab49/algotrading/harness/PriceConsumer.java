package com.lab49.algotrading.harness;

import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.marshaller.PricingWindowMarshaller;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: finish
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:23
 */
public class PriceConsumer implements Runnable {

    private final PricingWindowMarshaller marshaller = new PricingWindowMarshaller();

    private final BlockingQueue<Price> sharedQueue;
	PricingWindowMarshaller ONLY_ONE;

    public PriceConsumer(BlockingQueue<Price> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                marshaller.add(sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(PriceConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}