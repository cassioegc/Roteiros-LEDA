package adt.linkedList.batch;

import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import util.GenericException;

/**
 * Manipula elementos da LinkedList em bloco (batch).
 * 
 * @author campelo
 * @author adalberto
 *
 * @param <T>
 */
public class BatchLinkedListImpl<T> extends DoubleLinkedListImpl<T> implements BatchLinkedList<T> {

	/*
	 * Nao modifique nem remova este metodo.
	 */
	public BatchLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void inserirEmBatch(int posicao, T[] elementos) throws GenericException {
		if (elementos == null || posicao < 0) {
			throw new GenericException();
		}
		int parada = 0;
		int inicio = elementos.length - 1;
		if (size() == 0) {
			this.head = new DoubleLinkedListNode<>(elementos[0], new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<>());
			this.last = new DoubleLinkedListNode<>(elementos[inicio], new DoubleLinkedListNode<T>(),(DoubleLinkedListNode<T>) this.head);
			((DoubleLinkedListNode<T>) this.head).setNext(this.last);
			parada = 1;
			inicio -= 1;
		}
		int index = 0;
		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
		while (index < posicao - 1) {
			node = (DoubleLinkedListNode<T>) node.getNext();
			index = index + 1;
		}
		for (int i = inicio; i >= parada; i--) {
			DoubleLinkedListNode<T> aux = new DoubleLinkedListNode(elementos[i], ((DoubleLinkedListNode<T>) node.getNext()), node);
			((DoubleLinkedListNode<T>) node.getNext()).setPrevious(aux);
			node.setNext(aux);
		}

	}

	@Override
	public void removerEmBatch(int posicao, int quantidade) throws GenericException {
		if (posicao < 0 || posicao >= size() || quantidade > size() || (size() - posicao) > size()) {
			throw new GenericException();
		}
		int index = 0;
		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
		while (index < posicao) {
			node = (DoubleLinkedListNode<T>) node.getNext();
			index = index + 1;
		}
		int removidos = 0;
		DoubleLinkedListNode<T> aux = node;;
		while (removidos != quantidade) {
			node.getPrevious().setNext(aux.getNext());
			((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
			if (this.head.equals(aux)) {
				this.head = aux.getNext();
			}
			aux = (DoubleLinkedListNode<T>) node.getPrevious().getNext();
			removidos += 1;
			if (this.last.equals(aux.getNext())) {
				this.last = aux.getPrevious();
			}
		}
	}

	/*
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
	 * comecando a navegacao pelo Head
	 */
	public String toStringFromHead() {

		String result = "";
		DoubleLinkedListNode<T> aNode = (DoubleLinkedListNode<T>) getHead();

		while (!aNode.isNIL()) {

			if (!result.isEmpty()) {
				result += " ";
			}

			result += aNode.getData();
			aNode = (DoubleLinkedListNode<T>) aNode.getNext();

		}

		return result;
	}

	/*
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
	 * porem comecando a navegacao pelo Last
	 * 
	 * Este metodo produz o MESMO RESULTADO de toStringFromHead()
	 * 
	 */
	public String toStringFromLast() {

		String result = "";
		DoubleLinkedListNode<T> aNode = getLast();

		while (!aNode.isNIL()) {

			if (!result.isEmpty()) {
				result = " " + result;
			}

			result = aNode.getData() + result;
			aNode = (DoubleLinkedListNode<T>) aNode.getPrevious();

		}

		return result;
	}

	@Override
	public String toString() {
		return toStringFromHead();
	}
	
	@Override
	public int size() {
		int size = 0;
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
		while(!aux.isNIL()) {
			size += 1;
			aux = (DoubleLinkedListNode<T>) aux.getNext();
		}
		return size;
	}

}
