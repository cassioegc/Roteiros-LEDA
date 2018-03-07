package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		empty();
		insert(array);
		return this.order();
	}

	private void empty() {
		while (!this.isEmpty()) {
			this.remove(this.root.getData());
		}
	}

	private void insert(T[] array) {
		for (T t : array) {
			this.insert(t);
		}
	}

	@Override
	public T[] reverseOrder() {
		ArrayList<Comparable> array = new ArrayList<>();
		reverseOrder(array, this.root);
		return (T[]) array.toArray(new Comparable[size()]);

	}

	private void reverseOrder(ArrayList array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			reverseOrder(array, (BSTNode<T>) node.getRight());
			array.add(node.getData());
			reverseOrder(array, (BSTNode<T>) node.getLeft());

		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> result = null;
		if (element != null) {
			if (node.isEmpty() || this.comparator.compare(node.getData(), element) == 0) {
				result = node;
			} else if (this.comparator.compare(node.getData(), element) > 0) {
				result = search((BSTNode<T>) node.getLeft(), element);
			} else {
				result = search((BSTNode<T>) node.getRight(), element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.root, new BSTNode<>());
		}
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.setParent(parent);
		} else if (this.comparator.compare(element, node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight(), node);
		} else {
			insert(element, (BSTNode<T>) node.getLeft(), node);
		}
	}

}
