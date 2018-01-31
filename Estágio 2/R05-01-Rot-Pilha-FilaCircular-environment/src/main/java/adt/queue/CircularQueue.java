package adt.queue;

public class CircularQueue<T> implements Queue<T> {

   private T[] array;
   private int tail;
   private int head;
   private int elements;

   public CircularQueue(int size) {
      array = (T[]) new Object[size];
      head = -1;
      tail = -1;
      elements = 0;
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (isFull()) {
         throw new QueueOverflowException();
      } else if (element != null) {
         if (isEmpty()) {

            this.head = 0;
            this.tail = 0;
         } else {
            this.tail = (this.tail + 1) % array.length;
         }
      }
      this.array[this.tail] = element;
      this.elements += 1;
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      T element = null;
      if (!isEmpty()) {
         element = this.array[this.head];
         this.head = (this.head + 1) % array.length;
         this.elements -= 1;
      } else {
         throw new QueueUnderflowException();
      }
      if (this.elements == 0) {
         this.head = -1;
         this.tail = -1;
      }
      return element;
   }

   @Override
   public T head() {
      T element = null;
      if (!isEmpty()) {
         element = array[head];
      }
      return element;
   }

   @Override
   public boolean isEmpty() {
      return (this.tail == -1 && this.head == -1);
   }

   @Override
   public boolean isFull() {
      return this.elements == this.array.length;
   }

}
