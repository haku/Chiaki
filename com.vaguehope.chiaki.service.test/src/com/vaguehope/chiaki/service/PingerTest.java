package com.vaguehope.chiaki.service;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PingerTest {

	@Mock private ProducerTemplate producerTemplate;

	@Test
	public void itDoesSomething () throws Exception {
		assertNotNull(this.producerTemplate);
		Pinger p = new Pinger(this.producerTemplate);
		assertNotNull(p);
	}

}
