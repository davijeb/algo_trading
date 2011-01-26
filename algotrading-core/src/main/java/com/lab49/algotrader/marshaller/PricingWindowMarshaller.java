package com.lab49.algotrader.marshaller;

import com.lab49.algotrader.factory.PricingDimensionPublisherFactory;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.PricingDimensionPublisher;

import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The PricingWindowMarshaller accepts price objects and assigns them to the
 * correct publishing mechanism based on the the product name.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingWindowMarshaller {

    // Create an executor with a thread per processor
    public final Executor executor =
           Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final Map<String, PricingDimensionPublisher> publishers;

    public PricingWindowMarshaller() {
        publishers = PricingDimensionPublisherFactory.get();
    }

    /**
     * Given a price from the queue we add it to the correct publisher, based
     * on the product type.
     * @param price the price
     * @throws InterruptedException
     */
    public void add(final Price price) throws InterruptedException {

        /**
         * A special case of pricing object is the PriceReset case. This signifies
         * that we should reset all date currently in-memory. In our case it means
         * clearing all the PricingDimensionPublisher price list values.
         */
        if(price.reset()) {
			for(PricingDimensionPublisher pdp: publishers.values()) pdp.getPrices().clear();
            return;
        }

        /**
         * Get the publisher (currently two of them) based on the product name
         * and ask it to execute iff it has a correctly sized price window.
         *
         * As the reading from the queue is serialized then having dedicated publishers
         * that only care about a subset of the population means it can be parallelized
         * forward of this point.
         */
        if(publishers.get(price.getProductName()) == null) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, "Unable to publish unknown product name" + price);
        } else {
            publishers.get(price.getProductName()).add(price,executor);
        }
    }
}

