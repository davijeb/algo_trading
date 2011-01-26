package com.lab49.algotrader.factory;

import com.lab49.algotrader.TradingProperties;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * author: Jeremy Davies [jerdavies@gmail.com]
 */
public class PricingDimensionPublisherFactoryTest {

    @Test
    public void shouldHaveNotNullPricingDimensionPublisher() throws Exception {
         assertThat(PricingDimensionPublisherFactory.get(), notNullValue());
    }

    @Test
    public void shouldHaveSizeEqualToPricingWindowSize() throws Exception {
       int expected = TradingProperties.INSTANCE.getStringArray("product.names").length;
       assertThat(PricingDimensionPublisherFactory.get().size(), equalTo(expected));
    }
}
