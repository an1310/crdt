package org.erikspan.crdt.sets;

import org.erikspan.crdt.CRDTTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class CRDTSetTest<S extends Set<String>, R extends CRDTSet<String, S, R>>
		extends CRDTTest<S, R> {

	protected static final String OBJ_1 = "1";
	protected static final String OBJ_2 = "2";
	protected static final String OBJ_3 = "3";
	
	private static final String COMMA = ",";
	
	public abstract R newSet();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validateDefaults() {
		assertTrue(defaultCRDT().isEmpty());
		assertFalse(firstOrtho().isEmpty());
		assertFalse(secondOrtho().isEmpty());
		assertNotEquals(firstOrtho().value(), secondOrtho().value());
		assertNotEquals(firstOrtho().value(), firstAndSecond().value());
		assertNotEquals(secondOrtho().value(), firstAndSecond().value());
		assertTrue(OBJ_1.compareTo(OBJ_2) < 0) ;
		assertTrue(OBJ_2.compareTo(OBJ_3) < 0);
	}

	@Test
	public void addReturnsAbsence() {
		assertTrue(defaultCRDT().add(OBJ_1));
		assertFalse(defaultCRDT().add(OBJ_1));
	}

	@Test
	public void addToSetFunctions() {
		defaultCRDT().add(OBJ_1);

		assertTrue(defaultCRDT().contains(OBJ_1));
	}

	@Test
	public void containsAllOfSubset() {
		assertTrue(firstAndSecond().containsAll(firstOrtho()));
	}

	@Test
	public void doesNotContainAllOfSuperset() {
		assertFalse(firstOrtho().containsAll(firstAndSecond()));
	}

	@Test
	public void afterClearIsEmpty() {
		try {
			firstAndSecond().clear();

			assertTrue(firstAndSecond().isEmpty());
		} catch (UnsupportedOperationException ex) {
			fail();
		}
	}

	@Test
	public void removeShouldRemove() {

		defaultCRDT().add(OBJ_1);

		try {
			assertTrue(defaultCRDT().remove(OBJ_1));

			assertFalse(defaultCRDT().contains(OBJ_1));
		} catch (UnsupportedOperationException ex) {
			fail();
		}
	}
	
	@Test
	public void removeAllShouldRemove() {
		try {
			firstAndSecond().removeAll(firstOrtho());
			
			assertEquals(secondOrtho(), firstAndSecond());
		} catch (UnsupportedOperationException ex) {
			fail();
		}
	}
	
	@Test
	public void removeAllReturnsTrueWhenChanged() {
		try {
			assertTrue(firstAndSecond().removeAll(firstOrtho()));
		} catch (UnsupportedOperationException ex) {
			fail();
		}
	}
	
	@Test
	public void removeAllReturnsFalseWhenUnChanged() {
		try {
			assertFalse(firstOrtho().removeAll(secondOrtho()));
		} catch (UnsupportedOperationException ex) {
			fail();
		}
	}

	@Test
	public void removeReturnsFalseIfNotPresent() {

		try {
			assertFalse(defaultCRDT().remove(OBJ_1));
		} catch (UnsupportedOperationException ex) {
         fail();
		}
	}

	@Test
	public void retainRemoves() {
		try {
			firstAndSecond().retainAll(firstOrtho());

			assertEquals(firstOrtho(), firstAndSecond());
		} catch (UnsupportedOperationException ex) {
         fail();
		}
	}

	@Test
	public void retainReturnsTrueIfChanged() {
		try {
			assertTrue(firstAndSecond().retainAll(firstOrtho()));
		} catch (UnsupportedOperationException ex) {
         fail();
		}
	}

	@Test
	public void retainReturnsFalseIfUnchanged() {
		try {
			assertFalse(firstOrtho().retainAll(firstOrtho()));
		} catch (UnsupportedOperationException ex) {
         fail();
		}
	}

	@Test
	public void addAllAddsAll() {
		defaultCRDT().addAll(firstOrtho());

		assertTrue(defaultCRDT().containsAll(firstOrtho()));
	}

	@Test
	public void sizeChanges() {
		defaultCRDT().add(OBJ_1);

		assertEquals(1, defaultCRDT().size());
	}

	@Test
	public void addAllReturnsExistence() {
		assertTrue(defaultCRDT().add(OBJ_1));

		assertFalse(defaultCRDT().add(OBJ_1));
	}
	
	@Test
	public void mergeCombinesItems() {
		int o1 = firstOrtho().size();
		int o2 = secondOrtho().size();
		
		assertEquals(o1 + o2, firstOrtho().merge(secondOrtho()).size());
	}


	@Test
	public void toArrayGeneratesCorrectArray() {
		Object [] value = firstAndSecond().toArray();
		Arrays.sort(value);
		assertTrue(Arrays.equals(new String [] {OBJ_1, OBJ_2, OBJ_3}, value));
	}
	
	@Test
	public void toArrayWithArgGeneratesCorrectArray() {
		String [] value = firstAndSecond().toArray(new String[3]);
		Arrays.sort(value);
		assertTrue(Arrays.equals(new String [] {OBJ_1, OBJ_2, OBJ_3}, value));
	}


	@Test
	public void toStringContainsValues() {
		String value = firstAndSecond().toString();
		assertTrue(value.contains(OBJ_1));
      assertTrue(value.contains(OBJ_2));
      assertTrue(value.contains(OBJ_3));
      assertTrue(value.contains(COMMA));
	}
	
	@Test
	public void sizeConstantOnAddExisting() {
		R set = defaultCRDT();
		set.add(OBJ_1);
		set.add(OBJ_1);
		assertEquals(1, set.size());
	}
	
}
