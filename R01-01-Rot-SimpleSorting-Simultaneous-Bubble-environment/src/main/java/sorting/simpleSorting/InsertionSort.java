package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= 0 && leftIndex < rightIndex && rightIndex < array.length) {

            for (int i = leftIndex; i <= rightIndex; i++) {
                T aux = array[i];
                int index = i;

                while ((index > leftIndex) && (aux.compareTo(array[index - 1]) < 0)) {
                    array[index] = array[index - 1];
                    index--;
                }
                array[index] = aux;
            }
        }
    }
}
