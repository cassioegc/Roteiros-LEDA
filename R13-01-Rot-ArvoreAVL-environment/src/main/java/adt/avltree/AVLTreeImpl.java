package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.root, new BSTNode<>());
			this.rebalanceUp(search(element));
		}
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.setParent(parent);
		} else if (element.compareTo(node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight(), node);
		} else {
			insert(element, (BSTNode<T>) node.getLeft(), node);
		}
	}

	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (node != null && !node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (super.numberOfChildren(node) == 1) {
				if (!node.equals(this.root)) {
					if (node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					T newData = null;
					if (!node.getLeft().isEmpty()) {
						newData = super.treeMaximum((BSTNode<T>) node.getLeft()).getData();
					} else {
						newData = super.treeMinimum((BSTNode<T>) node.getRight()).getData();
					}
					remove(newData);
					this.root.setData(newData);
				}
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				T newData = sucessor.getData();
				remove(sucessor.getData());
				node.setData(newData);
			}
			if (node.isEmpty()) {
				this.rebalanceUp((BSTNode<T>) node.getParent());
			} else {
				this.rebalanceUp(node);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			result = heightSubTree((BSTNode<T>) node.getLeft()) - heightSubTree((BSTNode<T>) node.getRight());
		}
		return result;
	}

	private int heightSubTree(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			int lefth = heightSubTree((BSTNode<T>) node.getLeft());
			int righth = heightSubTree((BSTNode<T>) node.getRight());

			if (lefth > righth) {
				result = lefth + 1;
			} else {
				result = righth + 1;
			}
		}
		return result;
	}

	private boolean leftChildren(BSTNode<T> node, BSTNode<T> parent) {
		return parent.getLeft().equals(node);
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (Math.abs(balance) > 1) {
				BSTNode<T> aux = null;
				if (balance > 0) {
					if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
						node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
					}
					aux = Util.rightRotation(node);

				} else {
					if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
						node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
					}
					aux = Util.leftRotation(node);
				}

				if (this.root.equals(node)) {
					this.root = aux;
				} else {
					if (leftChildren(node, (BSTNode<T>) aux.getParent())) {
						aux.getParent().setLeft(aux);
					} else {
						aux.getParent().setRight(aux);
					}
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			while (!node.isEmpty()) {
				this.rebalance(node);
				node = (BSTNode<T>) node.getParent();
			}
		}
	}

}
