package org.erikspan.crdt.counters;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.erikspan.crdt.CRDTFactory;
import org.erikspan.crdt.inject.ClientId;

@Singleton
public class PNCounterFactory implements CRDTFactory<PNCounter> {
	
	private final ObjectMapper serializer;
	private final String clientId;
	
	@Inject
	public PNCounterFactory(final ObjectMapper mapper, @ClientId final String client) {
		this.serializer = mapper;
		this.clientId = client;
	}

	@Override
	public PNCounter create() {
		return new PNCounter(serializer, clientId);
	}

	@Override
	public PNCounter create(final byte[] payload) {
		return new PNCounter(serializer, clientId, payload);
	}
	
}
