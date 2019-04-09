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

        if ((array.length == 0) || array[0].equals(x)) {
            return null;
        }

        if (middle > 0 && array[middle] >= x && (array[middle - 1] < x || (array[middle] == x && array[middle - 1] ==x)))
        {
            return array[middle - 1];
        }
        if (array[middle] > x) {
            return floorAux(array, x, 0, middle - 1);
        }
        return floorAux(array, x, middle + 1, rightIndex);
    }

    @Override
    public Integer ceil(Integer[] array, Integer x) {
        return ceilAux(array, x, 0, array.length - 1);

    }

    private Integer ceilAux(Integer[] array, Integer x, int leftIndex, int rightIndex) {
        Integer middle = (leftIndex + rightIndex) / 2;

        if ((array.length == 0) || (array[array.length - 1].equals(x))) {
            return null;
        }

        if (middle < array.length - 1 && ((array[middle] <= x && array[middle + 1] > x) ||
                (array[middle] == x && array[middle + 1] == x))) {
            return array[middle + 1];
        }
        if (array[middle] > x) {
            return ceilAux(array, x, 0, middle - 1);
        }
        return ceilAux(array, x, middle + 1, rightIndex);


    }

}
