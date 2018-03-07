package adt.skipList.fromBST;

import java.util.ArrayList;

import adt.bst.BST;
import adt.bst.BSTNode;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class SkipListFromBSTImpl extends SkipListImpl<Integer> implements SkipListFromBST<Integer> {

	public SkipListFromBSTImpl(int maxHeight) {
		super(maxHeight);
	}

	public void importFromBST(BST<Integer> bst) {


		if (!bst.isEmpty()) {

			this.maxHeight = bst.height() + 1;
			this.resetSkipList(bst.height() + 1);
			ArrayList<BSTNode<Integer>> array = new ArrayList<>();
			ArrayList<Integer> height = new ArrayList<>();

			this.ordemDeInsercao(array, (BSTNode<Integer>) bst.getRoot());
			this.height((BSTNode<Integer>) bst.getRoot(), height, bst.height() + 1);
			this.insert(bst, array, height);
		} else {
			this.resetSkipList(this.maxHeight);
		}
	}
	
	/**
	 * Faz a insercao dos novos valores na skip list
	 * @param bst
	 * @param nodes
	 * @param height
	 */
	private void insert(BST bst, ArrayList<BSTNode<Integer>> nodes, ArrayList<Integer> height) {
		for (int i = 0; i < nodes.size(); i++) {
			this.insert(nodes.get(i).getData(), nodes.get(i).getData(), height.get(i));
		}
	}

	/**
	 * Reinicia uma skip list
	 * @param height
	 */
	private void resetSkipList(int height) {
		root = new SkipListNode(Integer.MIN_VALUE, height, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, height, null);
		for (int i = 0; i < maxHeight; i++) {
			this.root.getForward()[i] = this.NIL;
		}
	}

	/**
	 * Retorna as alturas que cada valor sera inserido na skip, ja retorna em ordem crescente dos valores dos nos.
	 * @param node
	 * @param array
	 * @param height
	 */
	private void height(BSTNode<Integer> node, ArrayList<Integer> array, int height) {
		if (!node.isEmpty()) {
			height((BSTNode<Integer>) node.getLeft(), array, height - 1);
			array.add(height);
			height((BSTNode<Integer>) node.getRight(), array, height - 1);

		}
	}
	/**
	 * Retorna todos os nós da bst em ordem crescente
	 * @param array
	 * @param node
	 */
	private void ordemDeInsercao(ArrayList<BSTNode<Integer>> array, BSTNode<Integer> node) {
		if (!node.isEmpty()) {
			ordemDeInsercao(array, (BSTNode<Integer>) node.getLeft());
			array.add((BSTNode<Integer>) node);
			ordemDeInsercao(array, (BSTNode<Integer>) node.getRight());

		}
	}

}
