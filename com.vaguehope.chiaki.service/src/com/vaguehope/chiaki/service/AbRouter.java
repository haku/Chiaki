package com.vaguehope.chiaki.service;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbRouter extends RouteBuilder {

	private static final String E_IN = "activemq:queue:chiaki.in";
	private static final String E_OUT = "activemq:queue:chiaki.out";

	private static final Logger LOG = LoggerFactory.getLogger(AbRouter.class);

	@Override
	public void configure () throws Exception {
		from(E_IN)
				.to(E_OUT);
		LOG.info("Route: {} --> {}.", E_IN, E_OUT);
	}

}
