package com.lab49.algotrader.factory;

import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.models.PricingDimensionProducer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO: finish
 *
 * @author Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingDimensionFactory {

    private static Map<String, PricingDimensionProducer> map;

    public static Map<String, PricingDimensionProducer> get() {

        if(map != null) return map;

        map = new ConcurrentHashMap<String, PricingDimensionProducer>();

        for(String product : TradingProperties.INSTANCE.getStringArray("product.names")) {
             map.put(product, new PricingDimensionProducer());
        }

        return map;
    }
}
