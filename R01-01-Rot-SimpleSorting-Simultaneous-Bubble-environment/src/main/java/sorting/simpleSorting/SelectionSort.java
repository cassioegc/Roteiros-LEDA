package sorting.simpleSorting;

import java.util.Arrays;

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
		int j = leftIndex;
		int maior = leftIndex;
		int r = rightIndex;
		while (rightIndex != j)
			for (int i = j; i < r; i++) {
				if (array[i].compareTo(array[maior]) > 0) {
					maior = i;
				}
			}
			Util.swap(array, maior, r);
			maior--;
			r--;
	}
	public static void main(String[] args) {
		Integer[] a = {5,4,9,2,7,3};
		AbstractSorting<Integer> sort = new SelectionSort<Integer>();
		sort.sort(a,0,4);
		System.out.println(Arrays.toString(a));
	}
}
