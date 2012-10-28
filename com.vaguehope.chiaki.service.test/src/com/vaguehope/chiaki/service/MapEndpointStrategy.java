package com.vaguehope.chiaki.service;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.spi.EndpointStrategy;

public class MapEndpointStrategy implements EndpointStrategy {

	final private Map<String, Endpoint> endpoints;

	public MapEndpointStrategy (Map<String, Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public Endpoint registerEndpoint (String uri, Endpoint endpoint) {
		Endpoint e = this.endpoints.get(uri);
		return e != null ? e : endpoint;
	}

}