package com.lab49.algotrading.harness.simulation;

import com.lab49.algotrader.models.price.Price;
import com.lab49.algotrading.harness.io.SerializableBlockingQueueWrapper;
import com.lab49.algotrading.harness.io.SerializableQueueWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PriceGeneratorTest {

	@Mock
	private SerializableQueueWrapper<Price> wrapper;
	@Mock
	private BlockingQueue<Price> sharedQueue;

	private PriceGenerator generator;

	@Before
	public void
	init() throws IOException, InterruptedException {
		generator = new PriceGenerator(wrapper, sharedQueue);
		generator.generate(10);
	}

	@Test
	public void testGeneratePopulation() throws Exception {

		int expected = 11; // 10 + 1 reset
		verify(sharedQueue, times(expected)).add(any(Price.class));
	}

	@Test
	public void testGenerateSerializesWrapper() throws Exception {
		verify(wrapper, times(1)).write();
	}

	@Test
	public void testGenerateClearsSharedQueue() throws Exception {
		verify(sharedQueue, times(1)).clear();
	}
}
