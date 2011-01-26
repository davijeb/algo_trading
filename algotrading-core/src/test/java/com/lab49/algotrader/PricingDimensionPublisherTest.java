package com.lab49.algotrader;

import static org.mockito.Mockito.*;

import com.lab49.algotrader.models.concurrent.PriceWindowConsumer;
import com.lab49.algotrader.models.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Executor;


@RunWith(MockitoJUnitRunner.class)
public class PricingDimensionPublisherTest {

	private PricingDimensionPublisher publisher = new PricingDimensionPublisher();

	@Mock
	private Executor executor;


	@Test
	public void testExecutorIsCalledWhenPublisherHasRequiredPriceData() throws InterruptedException {

		// When we add 4 price point we need to check the executor is fired.
		publisher.add(new Price("BP", 1), executor);
		publisher.add(new Price("BP", 2), executor);
		publisher.add(new Price("BP", 3), executor);
		publisher.add(new Price("BP", 4), executor);

		verify(executor, times(1)).execute(any(PriceWindowConsumer.class));
	}
}
