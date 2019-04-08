package orderStatistic;

import util.Util;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

    /**
     * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia
     * de usar o selection sem modificar o array original. Note que seu algoritmo vai
     * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem
     * desejada sem modificar o array original.
     * <p>
     * Restricoes:
     * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no
     * array original
     * - Nenhum array auxiliar deve ser criado e utilizado.
     * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
     * elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
     * como o selectionsort mas sem modificar nenhuma posicao do array).
     * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null.
     * - Considerar que k varia de 1 a N
     * - Sugestao: o uso de recursao ajudara sua codificacao.
     */
    @Override
    public T getOrderStatistics(T[] array, int k) {

        return selectionSort(array, k);
    }


    private T selectionSort(T[] array, int k) {


        for (int i = 0; i < k; i++) {

            int min = i;

            for (int j = i + 1; j < array.length; j++) {

                if (array[j].compareTo(array[min]) < 0) {

                    min = j;

                }
            }

            Util.swap(array, i, min);
        }
        return array[k - 1];
    }
}

