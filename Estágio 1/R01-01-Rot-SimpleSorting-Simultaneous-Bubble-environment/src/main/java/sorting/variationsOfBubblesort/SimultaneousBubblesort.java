package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex >= leftIndex
				&& array.length != 0) {
			int i = leftIndex;
			int j = rightIndex;
			while (i != j && i - j != 1) {
				int n = i;
				int k = j;
				while (n != j && k != i) {
					if (array[n].compareTo(array[n + 1]) > 0) {
						Util.swap(array, n, n + 1);
					}
					if (array[k].compareTo(array[k - 1]) < 0) {
						Util.swap(array, k, k - 1);
					}
					n++;
					k--;
				}
				i++;
				j--;
			}
		}
	}
}
