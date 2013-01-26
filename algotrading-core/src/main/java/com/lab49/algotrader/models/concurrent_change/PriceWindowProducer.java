package com.lab49.algotrader.models.concurrent_change;

import com.lab49.algotrader.models.Price;

import java.util.concurrent.BlockingQueue;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
class PriceWindowProducer implements Runnable {

    private final BlockingQueue sharedQueue;

    public PriceWindowProducer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
        // Sample data from
        sharedQueue.add(new Price("BP",   7.61));
        sharedQueue.add(new Price("RDSA", 2201.00));
        sharedQueue.add(new Price("RDSA", 2209.00));
        sharedQueue.add(new Price("BP",   7.66));
        sharedQueue.add(new Price("BP",   7.64));
        sharedQueue.add(new Price("BP",   7.67));       // should see this
    }

    @Override
    public void run() {


    }
}