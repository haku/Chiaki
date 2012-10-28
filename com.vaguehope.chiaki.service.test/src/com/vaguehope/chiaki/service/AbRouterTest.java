package com.vaguehope.chiaki.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbRouterTest {

	private static final String ACTIVEMQ_COMPONENT = "activemq";

	private CamelContext context;
	private ProducerTemplate producerTemplate;

	protected Endpoint chiakiIn;
	protected MockEndpoint chiakiOut;

	@Before
	public void before () throws Exception {
		this.context = new DefaultCamelContext(new SimpleRegistry());

		this.producerTemplate = this.context.createProducerTemplate();
		this.chiakiIn = this.context.getEndpoint("direct:chiaki.in");
		this.chiakiOut = (MockEndpoint) this.context.getEndpoint("mock:chiaki.out");

		this.context.addComponent(ACTIVEMQ_COMPONENT, this.context.getComponent("mock")); // Alias to anything that exists.
		Map<String, Endpoint> endpoints = new HashMap<String, Endpoint>();
		endpoints.put("activemq://queue:chiaki.in", this.chiakiIn);
		endpoints.put("activemq://queue:chiaki.out", this.chiakiOut);
		this.context.addRegisterEndpointCallback(new MapEndpointStrategy(endpoints));

		this.context.addRoutes(new AbRouter());
		this.context.start();
	}

	@After
	public void after () throws Exception {
		this.context.stop();
	}

	@Test
	public void itRoutesFromAtoB () throws Exception {
		final String testMsg = "Hello World";
		this.chiakiOut.expectedBodiesReceived(testMsg);
		this.producerTemplate.sendBody(this.chiakiIn, testMsg);
		this.chiakiOut.assertIsSatisfied();
	}

}
