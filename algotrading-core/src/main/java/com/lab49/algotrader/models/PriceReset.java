package com.lab49.algotrader.models;

/**
 * A standard price will not trigger a queue reset event
 * @return false
 */
public class PriceReset extends Price {

    public PriceReset() {
        super("PRICE FLOW RESET", 0);
    }

    /**
     * A resetting price is used to facilitate queue resetting
     * @see PricingWindowMarshaller
     * @return true
     */
    @Override
    public boolean reset() {
        return true;
    }
}
