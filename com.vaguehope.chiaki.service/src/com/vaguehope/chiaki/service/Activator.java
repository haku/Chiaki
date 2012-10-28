package com.vaguehope.chiaki.service;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO get CamelContext from OSGi service.
 * TODO extract config.
 */
public class Activator implements BundleActivator {

	private static final String BROKER_URL = "tcp://localhost:61616";

	private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

	private CamelContext camelContext;
	private Pinger pinger;

	@Override
	public void start (BundleContext context) throws Exception {
		this.camelContext = new DefaultCamelContext(new SimpleRegistry());
		this.camelContext.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(new ActiveMQConnectionFactory(BROKER_URL)));
		this.camelContext.start();

		this.camelContext.addRoutes(new AbRouter());

		this.pinger = new Pinger(this.camelContext.createProducerTemplate());
		this.pinger.start();

		LOG.info("Service started.");
	}

	@Override
	public void stop (BundleContext context) throws Exception {
		this.pinger.dispose();
		this.pinger = null;

		this.camelContext.stop();
		this.camelContext = null;

		LOG.info("Service stopped.");
	}

}
