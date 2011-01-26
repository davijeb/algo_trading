package com.lab49.algotrader.marshaller;

import static org.mockito.Mockito.*;

import com.lab49.algotrader.models.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PricingWindowMarshallerTest {

	private PricingWindowMarshaller pwm = new PricingWindowMarshaller();

	@Spy Price p = new Price("BP", 100);

	@Before public void
	init() throws InterruptedException {
		pwm.add(p);
	}

	@Test
	public void testResetCalled() throws Exception {
		verify(p, times(1)).reset();
	}
}
