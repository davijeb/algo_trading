package com.lab49.algotrader;

import com.lab49.algotrader.models.concurrent.PriceWindowConsumer;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.models.price.PricingWindow;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * The PricingDimensionPublisher accepts prices and attempts to create
 * a queue of a certain size. When the size is established it publishes
 * the prices to an executor using a PriceWindowConsumer.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingDimensionPublisher implements Cloneable {

    // How many prices do we want to analyse at any time?
    private static final transient int WINDOW_SIZE = TradingProperties.INSTANCE.getInteger("pricing.window.size");

    // The price store (remembering that this is divided by product already)
    private final List<Price> prices;

    public PricingDimensionPublisher() {
        prices = new LinkedList<Price>();
    }

    /**
     * Add a price to the price store. If the number of prices in the price store is equal
     * to the set window size then we execute a PriceWindowConsumer with a 'cloned' version
     * of this object.
     * @param p the price
     * @param executor a concurrent executor
     * @throws InterruptedException
     */
    public void add(final Price p, final Executor executor) throws InterruptedException {
        prices.add(p);
        if(prices.size() == WINDOW_SIZE) {
            executor.execute(new PriceWindowConsumer(new PricingWindow(this.clone())));
            prices.clear(); // cloned this previously to prevent any race conditions looking at stale data
        }
    }

    public List<Price> getPrices() {
        return prices;
    }

    /**
     * Perform a deep copy but look as though its a clone in this instance
     * @return a deep copy of the PricingDimensionPublisher
     */
    @Override
    protected PricingDimensionPublisher clone()  {

        PricingDimensionPublisher pd = new PricingDimensionPublisher();
        for(Price p: prices) {
            try {
                pd.getPrices().add((Price) p.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Unable to clone " + p);
            }
        }
        return pd;
    }

    @Override
    public String toString() {
        return prices.toString();
    }
}
