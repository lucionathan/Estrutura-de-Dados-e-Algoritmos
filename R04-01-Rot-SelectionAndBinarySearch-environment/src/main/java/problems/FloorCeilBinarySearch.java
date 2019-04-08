package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * <p>
 * Restricoes:
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais)
 * - O tempo de seu algoritmo deve ser O(log n).
 *
 * @author Adalberto
 */
public class FloorCeilBinarySearch implements FloorCeil {

    @Override
    public Integer floor(Integer[] array, Integer x) {
        return floorAux(array, x, 0, array.length - 1);

    }

    private Integer floorAux(Integer[] array, Integer x, int leftIndex, int rightIndex) {
        Integer middle = (leftIndex + rightIndex) / 2;

        if (rightIndex - leftIndex >= 1) {


            if (middle > 0) {
                if (array[middle - 1] < x && array[middle] >= x) {
                    return array[middle - 1];
                }
            }

            if (x > array[middle]) {
                floorAux(array, x, middle + 1, rightIndex);
            }
            floorAux(array, x, leftIndex, middle - 1);

        }
        return null;
    }

    @Override
    public Integer ceil(Integer[] array, Integer x) {
        return ceilAux(array, x, 0, array.length - 1);

    }

    private Integer ceilAux(Integer[] array, Integer x, int leftIndex, int rightIndex) {

        if (rightIndex - leftIndex >= 1) {

            Integer middle = (leftIndex + rightIndex) / 2;

            if (middle < array.length) {
                if (array[middle + 1] > x && array[middle] <= x) {
                    return array[middle + 1];
                }
            }

            if (x > array[middle]) {
                ceilAux(array, x, middle + 1, rightIndex);
            }
            ceilAux(array, x, leftIndex, middle - 1);

        }
        return null;
    }

}
