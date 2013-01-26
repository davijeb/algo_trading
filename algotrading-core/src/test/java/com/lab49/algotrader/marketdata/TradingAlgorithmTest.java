package com.lab49.algotrader.marketdata;

import com.lab49.algotrader.algos.TradingAlgorithm;
import com.lab49.algotrader.algos.TradingAlgorithmImpl;
import com.lab49.algotrader.utils.enums.DirectionEnum;
import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrader.models.trade.Trade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

// check the trading algorithm implementation is able
// build the correct Trade objects
public class TradingAlgorithmTest {

    private final TradingAlgorithm tradingAlgo = new TradingAlgorithmImpl();

    private BlockingQueue<Price> provider = new LinkedBlockingQueue<Price>();

    @Before public void
    init() {

        provider = new LinkedBlockingQueue<Price>();
    }

    @Test public void
    shouldHaveTwoProductNames() {
        assertThat(tradingAlgo.getProductNames().length, is(2));
    }

    @Test public void
    shouldReturnATradeForASinglePriceWithAValidProductName() {

        final Price price = new Price("BP", 7.67);
        final Trade expected = Trade.create(price, DirectionEnum.BUY);

        assertThat(tradingAlgo.buildTrade(price), equalTo(expected));
    }

    @Test(expected = RuntimeException.class) public void
    shouldThrowExceptionForASinglePriceWithAnInvalidProductName() {

        final Price price = new Price("INVALID_PRODUCT_NAME", 7.67);
        final Trade expected = Trade.create(price, DirectionEnum.BUY);

        tradingAlgo.buildTrade(price);
    }

    @Test public void
    shouldReturnAValidTradeForUpwardTrend() {

        final Trade expected = Trade.create(new Price("BP", 7.67), DirectionEnum.BUY);

        // call the helper method to gen upward trending data
        createUpwardTrend();

        List<Price> prices = new ArrayList<Price>(4);
        provider.drainTo(prices);

        assertThat(tradingAlgo.buildTrade(prices), equalTo(expected));

    }

    @Test public void
    shouldReturnANullTradeForDownwardTrend() {

        createDownwardTrend();

        List<Price> prices = new ArrayList<Price>(4);
        provider.drainTo(prices);

        assertThat(tradingAlgo.buildTrade(prices), nullValue());

    }

    private void createUpwardTrend() {
        provider.add(new Price("BP",   7.61));
        provider.add(new Price("BP",   7.66));
        provider.add(new Price("BP",   7.64));
        provider.add(new Price("BP",   7.67));
    }

    private void createDownwardTrend() {
        provider.add(new Price("BP",   7.64));
        provider.add(new Price("BP",   7.63));
        provider.add(new Price("BP",   7.62));
        provider.add(new Price("BP",   7.61));
    }
}
