package com.lab49.algotrader.models.price;

import com.lab49.algotrader.PricingDimensionPublisher;

import java.util.List;

/**
 * The PricingWindow which accepts a publisher for the sole reason of obtaining
 * the pricing information
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:22
 */
public class PricingWindow implements PriceWindow {

    private final PricingDimensionPublisher dimension;

    /**
     * TODO: Do we really need the publisher object here? We just
     * want the prices
     * @param dimension the publisher
     */
    public PricingWindow(PricingDimensionPublisher dimension) {
        this.dimension = dimension;
    }

    @Override
    public List<Price> getWindow() {
        return dimension.getPrices();
    }

    @Override
    public String toString() {
        return dimension.toString();
    }
}
