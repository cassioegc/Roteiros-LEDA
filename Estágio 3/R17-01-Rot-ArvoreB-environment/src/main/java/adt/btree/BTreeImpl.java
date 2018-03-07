package adt.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int height = -1;
		if (!this.isEmpty()) {
			while (!node.isEmpty()) {
				height += 1;
				if (node.getChildren().size() > 0) {
					node = node.getChildren().get(0);
				} else {
					node = new BNode<>(this.order);
				}
			}
		}
		return height;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		List<BNode<T>> array = new ArrayList<>();
		this.depthLeftOrder(array, this.root);
		BNode<T>[] aux = new BNode[array.size()];
		for (int i = 0; i < array.size(); i++) {
			aux[i] = array.get(i);
		}
		return aux;
	}

	private void depthLeftOrder(List<BNode<T>> array, BNode<T> node) {
		if (!node.isEmpty()) {
			array.add(node);
			for (BNode<T> aux : node.getChildren()) {
				depthLeftOrder(array, aux);
			}
		}
	}

	@Override
	public int size() {
		return this.size(this.root);
	}

	private int size(BNode<T> node) {
		int size = 0;
		if (!node.isEmpty()) {
			size += node.size();
			for (BNode<T> aux : node.getChildren()) {
				size += this.size(aux);
			}
		}
		return size;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return this.search(element, this.root);
	}

	private BNodePosition<T> search(T element, BNode<T> node) {
		int index = 0;
		if (!this.isEmpty()) {
			while (index < node.getElements().size() && element.compareTo(node.getElementAt(index)) > 0) {
				index += 1;
			}

			if (index <= node.getElements().size() && element.compareTo(node.getElementAt(index)) == 0) {
				return new BNodePosition<>(node.getChildren().get(index), index);
			}
			if (node.isEmpty()) {
				return new BNodePosition<>();
			}
		}
		return search(element, node.getChildren().get(index));
	}

	@Override
	public void insert(T element) {
		this.insert(element, this.root);
	}

	private void insert(T element, BNode<T> node) {
		if (node.isFull()) {
			this.split(node);
		}
		if (node.isLeaf()) {
			node.addElement(element);
		} else {
			int i = 0;

			while (i < node.size() && node.getElementAt(i).compareTo(element) < 0) {
				i++;
			}
			insert(element, node.getChildren().get(i));
		}
	}

	/**
	 * Transfere todos os valores a esquerda do valor mediano para o no left
	 * 
	 * @param node
	 * @param left
	 * @param index
	 */
	private void moveLeft(BNode<T> node, BNode<T> left, int index) {
		for (int i = 0; i < index; i++) {
			left.addElement(node.getElementAt(i));
			node.removeElement(i);
		}
	}

	/**
	 * Transfere todos os valores a direita do valor mediano para no right
	 * 
	 * @param node
	 * @param right
	 * @param index
	 */
	private void moveRight(BNode<T> node, BNode<T> right, int index) {
		for (int i = node.size() - 1; i > index; i--) {
			right.addElement(node.getElementAt(i));
			node.removeElement(i);
		}
	}
	/**
	 * Transfere os filhos a esquerda do valor medio para o no left
	 * @param node
	 * @param left
	 * @param medium
	 */
	private void moveChildrenLeft(BNode<T> node, BNode<T> left, int medium) {
		int index = 0;
		for (int i = 0; i < medium; i++) {
			left.addChild(index, node.getChildren().get(i));
			index += 1;
		}
	}
	/**
	 * Transfere os filhos a direita  do valor medio para o no righr
	 * @param node
	 * @param right
	 * @param medium
	 */
	private void moveChildrenRight(BNode<T> node, BNode<T> right, int medium) {
		int index = 0;
		for (int i = medium; i < node.getChildren().size(); i++) {
			right.addChild(index, node.getChildren().get(i));
			index += 1;
		}
	}

	private void split(BNode<T> node) {
		int medium = node.size() / 2;

		BNode<T> left = new BNode<>(this.order);
		BNode<T> right = new BNode<>(this.order);

		this.moveRight(node, right, medium);
		int mediumChildren = node.getChildren().size() / 2;
		if (node.getParent() == null || node.getParent().isFull()) {
			this.moveLeft(node, left, medium); 
			this.moveChildrenLeft(node, left, mediumChildren);
			this.moveChildrenRight(node, right, mediumChildren);

			node.setChildren(new LinkedList<BNode<T>>());
			node.addChild(0, left);
			node.addChild(1, right);

			left.setParent(node);
			right.setParent(node);
		
		} else {
			node.getParent().addElement(node.getElementAt(medium));
			node.removeElement(medium);
			this.moveChildrenRight(node, right, mediumChildren);
			right.setParent(node.getParent());
			int position = node.getParent().indexOfChild(node) + 1;
			node.getParent().addChild(position, right);
		} 
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
