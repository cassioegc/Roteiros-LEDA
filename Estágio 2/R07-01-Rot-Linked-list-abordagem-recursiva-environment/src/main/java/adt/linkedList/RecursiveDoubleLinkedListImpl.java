package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this);
		}
	}

	private void insert(T element, RecursiveDoubleLinkedListImpl<T> aux) {
		if (aux.getData() == null) {
			if (isEmpty()) {
				aux.setPrevious(new RecursiveDoubleLinkedListImpl<>());
			} else {
				aux.setPrevious(lastElement(this));
			}
			aux.setData(element);
			aux.setNext(new RecursiveDoubleLinkedListImpl<>());
		} else {
			RecursiveDoubleLinkedListImpl<T> new2 = new RecursiveDoubleLinkedListImpl<T>(element,
					new RecursiveDoubleLinkedListImpl<>(), lastElement(this));
			lastElement(this).setNext(new2);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>(this.data, this.next, this);
			this.data = element;
			this.next = aux;
			((RecursiveDoubleLinkedListImpl<T>) aux.getNext()).setPrevious(aux);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null && search(element) != null) {
			if (element.equals(this.data)) {
				removeFirst();
			} else {
				remove(element, this);
			}
		}
	}

	private void remove(T element, RecursiveDoubleLinkedListImpl<T> aux) {
		if (aux.getNext().getData().equals(element)) {
			aux.next = aux.getNext().getNext();
			((RecursiveDoubleLinkedListImpl<T>) aux.next).setPrevious(aux);
		} else {
			remove(element, (RecursiveDoubleLinkedListImpl<T>) aux.getNext());
		}
	}

	@Override
	public void removeFirst() {
		this.data = this.next.getData();
		((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
		this.next = this.next.getNext();
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			remove(lastElement(this).getData());
		}
	}

	private RecursiveDoubleLinkedListImpl<T> lastElement(RecursiveDoubleLinkedListImpl<T> aux) {
		RecursiveDoubleLinkedListImpl<T> result = null;
		if (aux.getNext().getData() == null) {
			result = aux;
		} else {
			result = lastElement((RecursiveDoubleLinkedListImpl<T>) aux.getNext());
		}
		return result;
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
