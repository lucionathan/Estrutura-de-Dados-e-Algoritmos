package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        int n = array.length;

        if (n != 1) {
            if (leftIndex < rightIndex && rightIndex < array.length && leftIndex >= 0) {
                int mid = (leftIndex + rightIndex) / 2;

                sort(array, leftIndex, mid);
                sort(array, mid + 1, rightIndex);
                merge(array, leftIndex, rightIndex, mid);
            }
        }

    }

    private void merge(T[] array, int left, int right, int mid) {

        T[] aux = (T[]) new Comparable[array.length];

        for (int i = left; i <= right; i++) {
            aux[i] = array[i];
        }

        int esquerda = left;
        int direita = mid + 1;
        int contador = left;

        while (esquerda <= mid && direita <= right) {
            if (aux[esquerda].compareTo(aux[direita]) < 0) {
                array[contador] = aux[esquerda];
                esquerda++;
            } else {
                array[contador] = aux[direita];
                direita++;
            }
            contador++;


        }

        while (esquerda <= mid) {
            array[contador] = aux[esquerda];
            contador++;
            esquerda++;
        }

        while (direita <= right) {
            array[contador] = aux[direita];
            direita++;
            contador++;
        }

    }
}
