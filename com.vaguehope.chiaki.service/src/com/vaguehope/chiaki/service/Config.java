package com.vaguehope.chiaki.service;

import org.apache.camel.CamelContext;
import org.apache.camel.guice.GuiceCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;


public class Config extends AbstractModule {

	private static final Logger LOG = LoggerFactory.getLogger(Config.class);

	@SuppressWarnings("restriction")
	@Override
	protected void configure () {
		LOG.info("Configuring...");
		bind(CamelContext.class).to(GuiceCamelContext.class).asEagerSingleton();
	}

}
