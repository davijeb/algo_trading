package com.lab49.algotrader.factory;

import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.PricingDimensionPublisher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO: finish
 * @author Jeremy Davies [jerdavies@gmail.com]
 * @version 1.0
 * @updated 27-Jan-2013 12:03:21
 */
public class PricingDimensionPublisherFactory {

    private static Map<String, PricingDimensionPublisher> map;

    public static Map<String, PricingDimensionPublisher> get() {

        if(map != null) return map;

        map = new ConcurrentHashMap<String, PricingDimensionPublisher>();

        for(String product : TradingProperties.INSTANCE.getStringArray("product.names")) {
             map.put(product, new PricingDimensionPublisher());
        }

        return map;
    }
}
