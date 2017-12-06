package adt.linkedList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl<>();
		lista2 = new RecursiveDoubleLinkedListImpl<>();
		lista3 = new RecursiveDoubleLinkedListImpl<>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
	
	@Test 
	public void testRemove() {
		lista3.removeLast();
		assertEquals(0, lista3.size());
	}
	@Test 
	public void testRemove2() {
		lista3.removeFirst();
		assertEquals(0, lista3.size());
	}
	@Test 
	public void testRemove3() {
		lista3.remove(1);
		assertEquals(0, lista3.size());
	}
	@Test 
	public void testRemove4() {
		lista3.remove(67);
		assertEquals(1, lista3.size());
	}
	@Test 
	public void test() {
		lista3.remove(1);
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
		lista3.insert(1);
		lista3.insertFirst(3);
		assertEquals(2, lista3.size());
		Assert.assertArrayEquals(new Integer[] {3,1}, lista3.toArray());
		lista3.removeFirst();
		Assert.assertArrayEquals(new Integer[] {1}, lista3.toArray());
		lista3.insertFirst(3);
		lista3.removeLast();
		Assert.assertArrayEquals(new Integer[] {3}, lista3.toArray());
		lista3.insert(1);
		lista3.remove(3);
		Assert.assertArrayEquals(new Integer[] {1}, lista3.toArray());
		lista3.insertFirst(3);
		lista3.remove(1);
		Assert.assertArrayEquals(new Integer[] {3}, lista3.toArray());

	}
}