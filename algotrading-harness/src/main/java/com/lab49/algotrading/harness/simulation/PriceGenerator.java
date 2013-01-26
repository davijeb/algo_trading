package com.lab49.algotrading.harness.simulation;

import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrading.harness.io.SerializableQueueWrapper;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * The PriceGenerator generates n Price points and adds them to the
 * shared queue. In addition it also adds the points to the
 * SerializableQueueWrapper, which allows the test to be re-run.
 *
 * author: Jeremy Davies [jerdavies@gmail.com]
 */
public class PriceGenerator {

    // serialized queue (now a list) which allows the price data to be saved to disk
    private final SerializableQueueWrapper<Price> wrapper;
    private final BlockingQueue<Price> sharedQueue;
    private final String[] products;

    public PriceGenerator(SerializableQueueWrapper<Price> wrapper, BlockingQueue<Price> sharedQueue) {
        this.wrapper = wrapper;
        this.sharedQueue = sharedQueue;
        products = TradingProperties.INSTANCE.getStringArray("product.names");
    }

    public void generate(final int prices) throws InterruptedException, IOException {

        wrapper.clear();
        //sharedQueue.removeAll(new ArrayList<Object>());
        sharedQueue.clear(); // tidy the queue before we start
        for(int i=0; i <prices; i++) {
            String product = products[((int)(Math.random() * 2))]; // get either BP or RDSA
            Price p = new Price(product, (int)(Math.random() * 1000));		   // 0 < price < 1000

            sharedQueue.add(p);
            wrapper.add(p);
            //Thread.sleep((long) (Math.random() * 500));  // artificial delay to stop a blizzard of trace
        }

        wrapper.write();
    }
}
