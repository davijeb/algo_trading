package com.lab49.algotrader.models;

import com.lab49.algotrader.models.concurrent_change.PriceWindowConsumer;

import java.util.*;

/**
 * TODO: text
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingDimensionProducer implements Cloneable {

    private final List<Price> prices;

    public PricingDimensionProducer() {
        prices = new LinkedList<Price>();
    }

    public void add(Price p) throws InterruptedException {
        prices.add(p);
        if(prices.size() == 4) {
            PricingWindowMarshaller.priceWindowExecutor.execute(new PriceWindowConsumer(new PricingWindowBean(this.clone())));
            prices.clear();
        }
    }

    public List<Price> getPrices() {
        return prices;
    }

    /**
     * Perform a deep copy but look as though its a clone in this instance
     * @return a deep copy of the PricingDimensionProducer
     */
    @Override
    protected PricingDimensionProducer clone()  {

        PricingDimensionProducer pd = new PricingDimensionProducer();
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
