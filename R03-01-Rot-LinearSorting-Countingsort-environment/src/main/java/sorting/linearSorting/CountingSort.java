package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        if (leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length && array.length != 0) {

            Integer[] aux = new Integer[getMaior(array, leftIndex, rightIndex) + 1];

            for (int i = 0; i < aux.length; i++) {
                aux[i] = 0;
            }

            for (int i = leftIndex; i <= rightIndex; i++) {
                aux[array[i]]++;
            }

            for (int i = 1; i < aux.length; i++) {
                aux[i] = aux[i] + aux[i - 1];
            }

            Integer[] modelo = new Integer[array.length];

            for (int i = rightIndex; i >= leftIndex; i--) {
                modelo[aux[array[i]] - 1] = array[i];
                aux[array[i]]--;
            }

            for (int i = leftIndex; i <= rightIndex; i++) {
                array[i] = modelo[i];
            }
        }

    }

    private int getMaior(Integer[] array, int leftIndex, int rightIndex) {
        int maior = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (maior < array[i]) {
                maior = array[i];
            }
        }

        return maior;
    }


}
