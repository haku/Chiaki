package com.vaguehope.chiaki.service;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pinger extends TimerTask {

	private static final String ENDPOINT_URL = "activemq:topic:example.foo";
	private static final long DELAY = 10L * 1000L; // 10 seconds.

	private static final Logger LOG = LoggerFactory.getLogger(Pinger.class);

	private final Timer timer = new Timer();
	private final ProducerTemplate producerTemplate;

	public Pinger (ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}

	public void start () {
		this.timer.scheduleAtFixedRate(this, DELAY, DELAY);
		LOG.info("Pinger scheduled.");
	}

	public void dispose () {
		this.timer.cancel();
		LOG.info("Pinger disposed.");
	}

	@Override
	public void run () {
		this.producerTemplate.sendBody(ENDPOINT_URL, "desu~");
		LOG.debug("Pinger send to {}.", ENDPOINT_URL);
	}

}
