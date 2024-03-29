package com.lab49.algotrader.properties;

import com.lab49.algotrader.TradingProperties;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// check the property file singleton reader works
public class PropertiesTest {

	@Test public void
	shouldReturnAValidNonZeroIntegerForPricingWindowSize() {
		assertThat(TradingProperties.INSTANCE.getInteger("pricing.window.size"), instanceOf(Integer.class));
	}

	@Test public void
	shouldReturnAValidNonEmptyStringArrayForAllowedProductTypes() {
		assertThat(TradingProperties.INSTANCE.getStringArray("product.names"), arrayWithSize(greaterThan(0)));
	}

	@Test public void
	shouldReturnAValidIntegerForTradingQuantity() {
		assertThat(TradingProperties.INSTANCE.getInteger("trading.quantity"), instanceOf(Integer.class));
	}
}
