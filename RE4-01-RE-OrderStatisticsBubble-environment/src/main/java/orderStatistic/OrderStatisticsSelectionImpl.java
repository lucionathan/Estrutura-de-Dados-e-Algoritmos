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
     * elementos maiores/menores, mas sim poraplciar sucessivas selecoes.
     * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null.
     * - Considerar que k varia de 1 a N
     * - Sugestao: o uso de recursao ajudara sua codificacao.
     */
    @Override
    public T getOrderStatistics(T[] array, int k) {

        return getOrderStatisticsAux(array, k, getMenor(array), getMaior(array));

    }

    private T getOrderStatisticsAux(T[] array, int k, T menor, T maior) {
        if (k > 1) {
            T menorNovo = menor;

            for (int i = 0; i < array.length; i++) {
                if (menorNovo.compareTo(array[i]) > 0 && menorNovo.compareTo(maior) < 0) {
                    menorNovo = array[i];
                }
            }

            return getOrderStatisticsAux(array, -1, menor, maior);
        }

        return menor;
    }

    private T getMenor(T[] array) {

        T menor = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(menor) < 0) {
                menor = array[i];
            }
        }
        return menor;
    }

    private T getMaior(T[] array) {
        T maior = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(maior) > 0) {
                maior = array[i];
            }
        }
        return maior;
    }
}

