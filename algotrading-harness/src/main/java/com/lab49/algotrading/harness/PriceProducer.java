package com.lab49.algotrading.harness;

import com.lab49.algotrader.models.price.Price;

import java.util.concurrent.BlockingQueue;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
class PriceProducer implements Runnable {

    private final BlockingQueue<Price> sharedQueue;

    public PriceProducer(BlockingQueue<Price> sharedQueue) {
        this.sharedQueue = sharedQueue;
        // Sample data from
//        sharedQueue.add(new Price("BP",   7.61));
//        sharedQueue.add(new Price("RDSA", 2201.00));
//        sharedQueue.add(new Price("RDSA", 2209.00));
//        sharedQueue.add(new Price("BP",   7.66));
//        sharedQueue.add(new Price("BP",   7.64));
//        sharedQueue.add(new Price("BP",   7.67));       // should see this
    }

    @Override
    public void run() {


    }
}