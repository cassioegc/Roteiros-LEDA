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
			int pivot = leftIndex;
			
			while (pivot < rightIndex) {
				
				if (array[pivot+1].compareTo(array[pivot]) >= 0) {
					pivot++;
				}
				else if (array[pivot+1].compareTo(array[pivot]) < 0) {
					Util.swap(array, pivot, pivot+1);
					pivot--;
				}
				if (pivot == leftIndex-1) {
					pivot++;
				}
			}
		}
	}
	public static void main(String[] args) {
		AbstractSorting<Integer> s = new GnomeSort<>();
		Integer[] a = new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				1 };
		s.sort(a,0,9);
	}
}
