package adt.queue;

import java.util.Arrays;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T element = null;
		if (!isEmpty()) {
			element = array[tail];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 1; i < array.length; i++) {
			array[i-1] = array[i]; 
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (!isFull()) {
			if (element != null) {
				tail = tail + 1;
				array[tail] = element;
			}
		}
		else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element = null;
		if (!isEmpty()) {
			element = array[0];
			shiftLeft();
			tail = tail - 1;
		}
		else {
			throw new QueueUnderflowException();
		}
		return element;
	}
	//===========================================================
	public String toString() {
		return Arrays.toString(array);
	}
	
	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		QueueImpl<Integer> q = new QueueImpl<>(3);
		q.enqueue(2);
		q.enqueue(5);
		q.enqueue(8);
		System.out.println(q.toString() + " " + q.isFull() );
		System.out.println(q.dequeue());
		System.out.println(q.toString());
		System.out.println(q.isFull());
		
	}

}
