package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return size(this.data, this.next);
	}

	private int size(T data, RecursiveSingleLinkedListImpl<T> next) {
		int size = 0;
		if (data != null) {
			size += 1 + size(next.getData(), next.getNext());
		}
		return size;
	}

	@Override
	public T search(T element) {
		return search(element, this.data, this.next);
	}

	private T search(T element, T data, RecursiveSingleLinkedListImpl<T> next) {
		T result = null;
		if (data != null) {
			if (element.equals(data)) {
				result = data;
			} else {
				result = search(element, next.getData(), next.getNext());
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this);
		}
	}

	private void insert(T element, RecursiveSingleLinkedListImpl<T> aux) {
		if (aux.getData() == null) {
			aux.setData(element);
			aux.setNext(new RecursiveSingleLinkedListImpl<>());
		} else {
			insert(element, aux.getNext());
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null && search(element) != null) {
			remove(element, this);
		}
	}

	private void remove(T element, RecursiveSingleLinkedListImpl<T> aux) {
		if (aux.getData().equals(element)) {
			aux.setData(aux.getNext().getData());
			aux.setNext(aux.getNext().getNext());
		} else {
			remove(element, aux.getNext());
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		toArray(array, this, 0);
		return array;
	}

	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> aux, int posicao) {
		if (aux.getData() != null) {
			array[posicao] = aux.getData();
			toArray(array, aux.getNext(), ++posicao);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
