package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex && array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex > 0) {
			int meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}

	private void merge(T[] array, int ini, int meio, int fim) {
		T[] newArray = Arrays.copyOf(array, array.length);
		int i = ini;
		int j = meio + 1;
		int k = ini;
		
		while (i <= meio && j <= fim) {
			if (newArray[i].compareTo(newArray[j]) < 0) {
				array[k] = newArray[i];
				i++;
			} else {
				array[k] = newArray[j];
				j++;
			}
			k++;
		}
		while (i <= meio) {
			array[k] = newArray[i];
			k++;
			i++;
		}
		while (j <= fim) {
			array[k] = newArray[j];
			k++;
			j++;
		}
	}
}
