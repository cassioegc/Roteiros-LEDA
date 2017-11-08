package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex >= leftIndex
				&& array.length != 0) {
			int pivot = leftIndex + 1;
			
			while (pivot < rightIndex - 1) {
				
				if (pivot == leftIndex) {
					pivot++;
				}
				if (array[pivot+1].compareTo(array[pivot]) >= 0) {
					pivot++;
				}
				else if (array[pivot+1].compareTo(array[pivot]) < 0) {
					Util.swap(array, pivot, pivot+1);
					pivot--;
				}
			}
		}
	}
}
