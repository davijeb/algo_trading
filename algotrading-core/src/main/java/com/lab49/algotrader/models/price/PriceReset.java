package com.lab49.algotrader.models.price;

/**
 * A standard price will not trigger a queue reset event. This one will. It
 * signifies a termination of all data up to this point in the queue.
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PriceReset extends Price {

    public PriceReset() {
        super("PRICE FLOW RESET", 0);
    }

    /**
     * A resetting price is used to facilitate queue resetting
     * @see com.lab49.algotrader.marshaller.PricingWindowMarshaller
     * @return true
     */
    @Override
    public boolean reset() {
        return true;
    }
}
