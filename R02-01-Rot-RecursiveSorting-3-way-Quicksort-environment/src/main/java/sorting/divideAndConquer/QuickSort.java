package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (leftIndex < rightIndex && array != null && leftIndex >= 0 && rightIndex < array.length) {

         int pivot = leftIndex;
         int i = leftIndex;

         for (int n = leftIndex + 1; n <= rightIndex; n++) {
            if (array[n].compareTo(array[pivot]) <= 0) {
               i++;
               Util.swap(array, i, n);
            }
         }
         Util.swap(array, pivot, i);
         sort(array, leftIndex, i - 1);
         sort(array, i + 1, rightIndex);
      }
   }
}
