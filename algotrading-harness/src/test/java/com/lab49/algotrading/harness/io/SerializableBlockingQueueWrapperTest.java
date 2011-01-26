package com.lab49.algotrading.harness.io;

import com.lab49.algotrader.models.price.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SerializableBlockingQueueWrapperTest {

	@Spy
	private SerializableQueueWrapper<Price> wrapper= new SerializableBlockingQueueWrapper(new LinkedBlockingQueue<Price>());

	@Before public void
	init() throws IOException {

		wrapper.add(new Price("BP",1));
		wrapper.add(new Price("BP",2));
		wrapper.write();
	}

	@Test
	public void testRead() throws Exception {
		assertThat(wrapper.read().size(), is(2));
	}

	@Test
	public void testAdd() throws Exception {
		wrapper.add(new Price("BP",3));
		wrapper.write();
		assertThat(wrapper.read().size(), is(3));
	}

	@Test
	public void testRerun() throws Exception {
		wrapper.rerun();
		verify(wrapper, times(1)).read();
	}

	@Test
	public void testClear() throws Exception {
		wrapper.clear();
		wrapper.write();
		assertThat(wrapper.read().size(), is(0));

	}
}
