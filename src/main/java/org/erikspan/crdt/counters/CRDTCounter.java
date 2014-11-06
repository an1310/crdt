package org.erikspan.crdt.counters;

import org.erikspan.crdt.CRDT;

/**
 * Conflict-free replicated counters. 
 *
 * @param <S> The return type. 
 * @param <R> The counter type. 
 */
public interface CRDTCounter<S extends Number, R extends CRDT<S, R>> extends CRDT<S, R> {
	/**
	 * Increments the counter.
	 * 
	 * @return The current value of the counter. 
	 */
	S increment();
	
	/**
	 * Increments the counter by the amount n.
	 * 
	 * @return The current value of the counter. 
	 */
	S increment(int n);
	
	/**
	 * Decrements the counter. 
	 * 
	 * @return The current value of the counter. 
	 * @throws UnsupportedOperationException If it is not implemented. 
	 */
	S decrement();
	
	/**
	 * Decrements the counter by the amount n.
	 * 
	 * @return The current value of the counter. 
	 * @throws UnsupportedOperationException If it is not implemented. 
	 */
	S decrement(int n);
}
