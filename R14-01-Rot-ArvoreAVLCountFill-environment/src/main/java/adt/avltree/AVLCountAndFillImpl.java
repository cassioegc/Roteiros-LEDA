package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (Math.abs(balance) > 1) {
				BSTNode<T> aux = null;
				boolean doubleRotation = false;
				if (balance > 0) {
					if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
						node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
						this.LRcounter++;
						doubleRotation = true;
					}
					aux = Util.rightRotation(node);
					if (doubleRotation == false) {
						this.LLcounter++;
					}

				} else {
					if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
						node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
						this.RLcounter++;
						doubleRotation = true;
					}
					aux = Util.leftRotation(node);
					if (doubleRotation == false) {
						this.RRcounter++;
					}
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

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}
	
	private void removeDouble(List<T> array) {
		for (int i = 0; i < array.size(); i++) {
			for (int j = i + 1; j < array.size(); j++) {
				if (array.get(i).equals(array.get(j))) {
					array.remove(array.get(j));
				}
			}
		}
	}
 
	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null) {
			T[] preOrder = this.preOrder();
			List<T> all = new ArrayList<>();
			Collections.addAll(all, array);
			Collections.addAll(all, preOrder);
			Collections.sort(all);
			removeDouble(all);
			this.root = new BSTNode<T>();
			T[] add = this.getArrayInsert((T[]) all.toArray(new Comparable[all.size()]));
			for (int i = 0; i < add.length; i++) {
				this.insert(add[i]);
			}
		}

	}

	private ArrayList<T> newArrayList(ArrayList<T> array, int a, int f) {
		ArrayList<T> temp = new ArrayList<>();

		for (int i = a; i < f; i++) {
			temp.add(array.get(i));
		}
		return temp;
	}

	private T[] getArrayInsert(T[] array) {
		ArrayList<ArrayList<T>> matriz = new ArrayList<>();
		T[] aux = (T[]) new Comparable[array.length];
		matriz.add(new ArrayList<T>(Arrays.asList(array)));
		int i = 0;
		while (i < array.length) {
			int meio = matriz.get(i).size() / 2;
			aux[i] = matriz.get(i).get(meio);
			matriz.add(newArrayList(matriz.get(i), 0, meio));
			matriz.add(newArrayList(matriz.get(i), meio + 1, matriz.get(i).size()));
			i++;
		}
		return aux;
	}

}
