package com.lab49.algotrader.models;

import com.lab49.algotrader.factory.PricingDimensionFactory;
import com.lab49.algotrader.models.concurrent_change.PriceWindowConsumer;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * The PricingWindowMarshaller TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingWindowMarshaller {

    public final static Executor priceWindowExecutor =
           Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    Map<String, PricingDimensionProducer> pricingMap;

    public PricingWindowMarshaller() {
        pricingMap = PricingDimensionFactory.get();
    }

    public void add(final Price p) throws InterruptedException {

        if(p.reset()) {
            for(PricingDimensionProducer pdp: pricingMap.values()) pdp.getPrices().clear();
            return;
        }

        pricingMap.get(p.getProductName()).add(p);
    }

}

