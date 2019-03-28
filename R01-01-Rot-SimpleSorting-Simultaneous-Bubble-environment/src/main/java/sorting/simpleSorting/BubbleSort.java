package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            for (int x = leftIndex = 0; leftIndex < rightIndex - i; x++) {
                if (array[x].compareTo(array[x + 1]) > 0) {
                    Util.swap(array, x, x + 1);
                }
            }
        }
    }
}
