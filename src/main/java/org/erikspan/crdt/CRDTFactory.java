package org.erikspan.crdt;

/**
 * A factory for producing a specific CRDT.
 *
 * @param <T>  The implementing CRDT type.
 */
public interface CRDTFactory<T extends CRDT<?, T>> {
	T create();
	T create(byte [] payload);
}
