package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
        AbstractSorting<T> {

    /**
     * Implementação recursiva do selection sort. Você deve implementar apenas
     * esse método sem usar nenhum outro método auxiliar (exceto
     * Util.swap(array,int,int)). Para isso, tente definir o caso base do
     * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
     * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
     * quadrática O(n^2).
     */
    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {

        T min = array[leftIndex];
        int index = leftIndex;

        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
                index = i;
            }
        }

        Util.swap(array, index, leftIndex);
        sort(array, leftIndex + 1, rightIndex);

    }

}
