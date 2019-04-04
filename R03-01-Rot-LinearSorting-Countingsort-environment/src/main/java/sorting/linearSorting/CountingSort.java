package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        Integer[] result = criaArrayFinal(array, leftIndex);

        int min = getMenor(array, leftIndex, rightIndex);

        Integer[] temp = criaArrayAux(array, leftIndex, rightIndex, min);


        for (int i = leftIndex; i < rightIndex; i++) {

            temp[array[i] - min] = temp[array[i] - min]++;
        }

        for (int i = 1; i < temp.length; i++) {

            temp[i] = temp[i] + temp[i - 1];

        }

        for (int i = array.length; i >= 0; i--) {

            result[temp[array[i] - min]] = array[i];
            temp[array[i] - min] = temp[array[i] - min]--;

        }
    }


    private Integer[] criaArrayFinal(Integer[] array, int leftIndex) {

        Integer[] result = new Integer[array.length];

        for (int i = 0; i < leftIndex; i++) {
            result[i] = array[i];
        }

        return result;

    }


    private Integer[] criaArrayAux(Integer[] array, int leftIndex, int rightIndex, int min) {

        Integer[] retorno;
        int max = array[leftIndex];

        for (int i = leftIndex; i < rightIndex; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        max = max - min;

        retorno = new Integer[max];

        return retorno;
    }

    private int getMenor(Integer[] array, int leftIndex, int rightIndex) {

        int min = array[leftIndex];

        for (int i = leftIndex; i < rightIndex; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;

    }

}
