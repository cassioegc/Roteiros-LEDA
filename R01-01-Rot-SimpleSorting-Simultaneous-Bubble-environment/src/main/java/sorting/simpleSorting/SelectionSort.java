package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex >= leftIndex
				&& array.length != 0) {
			int r = rightIndex;
			while (r > leftIndex) {
				int maior = leftIndex;
				for (int i = leftIndex; i <= r; i++) {
					if (array[i].compareTo(array[maior]) > 0) {
						maior = i;
					}
				}
				Util.swap(array, r, maior);
				r--;
			}
		}
	}
}
