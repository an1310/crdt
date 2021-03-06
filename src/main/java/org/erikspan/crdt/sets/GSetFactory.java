package org.erikspan.crdt.sets;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.erikspan.crdt.CRDTFactory;

@Singleton
public class GSetFactory<E> implements CRDTFactory<GSet<E>> {
	
	private final ObjectMapper serializer;
	
	@Inject
	public GSetFactory(final ObjectMapper mapper) {
		this.serializer = mapper;
	}

	@Override
	public GSet<E> create() {
		return new GSet<E>(serializer);
	}

	@Override
	public GSet<E> create(final byte[] payload) {
		return new GSet<E>(serializer, payload);
	}

}
