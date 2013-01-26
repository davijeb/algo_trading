package com.lab49.algotrader.models;

import java.util.List;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingWindowBean implements PriceWindow {

    private final PricingDimensionProducer dimension;

    public PricingWindowBean(PricingDimensionProducer dimension) {
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
