package com.lab49.algotrader.movingaverage;

import com.lab49.algotrader.TradingProperties;
import com.lab49.algotrader.calcs.Calculator;
import com.lab49.algotrader.models.price.Price;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

// a number of tests to ensure the calculators are able to
// detect an upward moving average
public abstract class AbstractMovingAverageTest {

    private final Calculator calculator;

	private final int WINDOW_SIZE = TradingProperties.INSTANCE.getInteger("pricing.window.size");

    public AbstractMovingAverageTest(final Calculator calculator) {
        this.calculator = calculator;
    }

    @Test
    public void
    shouldReturnNewestPriceWhenTrendIsUpwards() {

        final Price expected = new Price("BP",4.0);

        List<Price> prices = new ArrayList<Price>() {
            {
                add(new Price("BP",1.0));
                add(new Price("BP",2.0));
                add(new Price("BP",3.0));
                add(new Price("BP",4.0));
            }
        };

        assertThat(calculator.calc(prices), equalTo(expected));
    }

    @Test public void
    shouldReturnNullWhenTrendIsDownwards() {

        final Price expected = null;

        List<Price> prices = new ArrayList<Price>() {
            {
                add(new Price("BP",10.0));
                add(new Price("BP",9.0));
                add(new Price("BP",8.0));
                add(new Price("BP",7.0));
            }
        };

        assertThat(calculator.calc(prices), equalTo(expected));
    }

    @Test public void
    shouldReturnNullWhenTrendIsFlat() {

        final Price expected = null;

        List<Price> prices = new ArrayList<Price>() {
            {
                add(new Price("BP",4.0));
                add(new Price("BP",4.0));
                add(new Price("BP",4.0));
                add(new Price("BP",4.0));
            }
        };

        assertThat(calculator.calc(prices), equalTo(expected));
    }

    @Test(expected = RuntimeException.class) public void
    shouldThrowExceptionWhenInputPopulationIsNull() {

        calculator.calc(null);
    }

    @Test(expected = RuntimeException.class) public void
    shouldThrowExceptionWhenInputPopulationSizeIsOneBiggerThanWindow() {

        List<Price> prices = new ArrayList<Price>() {

			{
				for(int i=0; i < WINDOW_SIZE+1; i++) {
                	add(new Price("BP",i));
            	}
			}
        };

        calculator.calc(prices);
    }
}
