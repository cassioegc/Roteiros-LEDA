package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex >= leftIndex
				&& array.length != 0) {

			int gap = nextGap(rightIndex - leftIndex);

			while (gap >= 1) {
				for (int i = leftIndex; i + gap <= (rightIndex - leftIndex); i++) {
					if (array[i].compareTo(array[i+gap]) > 0) {
						Util.swap(array, i, i+gap);
						
					}
				}
				gap = nextGap(gap);
			}
		}
	}

	private int nextGap(int gap) {
		return (int) (gap / 1.25);
	}
}
