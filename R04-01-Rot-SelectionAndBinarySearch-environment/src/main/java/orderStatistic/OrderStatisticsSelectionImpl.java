package orderStatistic;

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

      return selectionSort(array, k, selectSmaller(array, k), sellectBigger(array, k));

   }

   private T selectSmaller(T[] array, int k) {

      T min = array[0];
      for (int i = 1; i < array.length; i++) {

         if (array[i].compareTo(min) < 0) {

            min = array[i];

         }
      }
      return min;
   }

   private T sellectBigger(T[] array, int k) {

      T max = array[0];
      for (int i = 1; i < array.length; i++) {

         if (array[i].compareTo(max) > 0) {

            max = array[i];

         }
      }
      return max;
   }

   private T selectionSort(T[] array, int k, T menor, T maior) {
      if (k > 1) {

         T menor2 = maior;

         for (int i = 0; i < array.length; i++) {
            if ((array[i].compareTo(menor) > 0) && array[i].compareTo(menor2) < 0) {
               menor2 = array[i];
            }
         }

         return selectionSort(array, k - 1, menor2, maior);
      }
      return menor;

   }
}
