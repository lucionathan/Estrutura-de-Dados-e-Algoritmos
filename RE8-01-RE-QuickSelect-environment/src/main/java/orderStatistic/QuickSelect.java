package orderStatistic;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * <p>
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author Adalberto
 */
public class QuickSelect<T extends Comparable<T>> {

    /**
     * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o
     * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
     * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
     * e particiona os daods em duas partes baseado no pivot (exatemente da
     * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
     * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
     * elemento). Isso redua a completixade de O(n.log n) para O(n).
     * <p>
     * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
     * fora do tamanho do array, o metodo deve retornar null.
     *
     * @param array o array de dados a procurar o k-esimo menor elemento.
     * @param k     a ordem do elemento desejado. 1 significa primeiro menor
     *              elemento, 2 significa segundo menor elemento e assim por
     *              diante
     * @return
     */
    public T quickSelect(T[] array, int k) {

        if (k < 1 || k > array.length) {
            return null;
        }

        return menor(array, 0, array.length - 1, k - 1);
    }

    private T menor(T[] array, int inicio, int fim, int k) {
        if (inicio <= fim) {
            int pivot = partition(array, inicio, fim);

            if (pivot == k) {
                return array[pivot];
            } else if (pivot > k) {
                return menor(array, inicio, pivot - 1, k);
            } else {
                return menor(array, pivot + 1, fim, k);
            }
        }
        return null;


    }

    private int partition(T[] array, int inicio, int fim) {

        T pivot = array[inicio];
        int indexPivot = inicio;

        for (int i = indexPivot + 1; i <= fim; i++) {
            if (array[i].compareTo(pivot) < 0) {
                indexPivot++;
                swap(array, i, indexPivot);
            }
        }
        swap(array, inicio, indexPivot);
        return indexPivot;

    }

    private void swap(T[] arr, int x, int y) {
        T temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
