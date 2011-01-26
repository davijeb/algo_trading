package com.lab49.algotrader.models.price;

import com.lab49.algotrader.PricingDimensionPublisher;

import java.util.List;

/**
 * The PricingWindow which accepts a publisher for the sole reason of obtaining
 * the pricing information
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingWindow implements PriceWindow {

    private final PricingDimensionPublisher dimension;

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
