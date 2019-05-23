package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import util.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashtableClosedAddressImpl<T> extends
        AbstractHashtableClosedAddress<T> {

    /**
     * A hash table with closed address works with a hash function with closed
     * address. Such a function can follow one of these methods: DIVISION or
     * MULTIPLICATION. In the DIVISION method, it is useful to change the size
     * of the table to an integer that is prime. This can be achieved by
     * producing such a prime number that is bigger and close to the desired
     * size.
     * <p>
     * For doing that, you have auxiliary methods: Util.isPrime and
     * getPrimeAbove as documented bellow.
     * <p>
     * The length of the internal table must be the immediate prime number
     * greater than the given size (or the given size, if it is already prime).
     * For example, if size=10 then the length must
     * be 11. If size=20, the length must be 23. You must implement this idea in
     * the auxiliary method getPrimeAbove(int size) and use it.
     *
     * @param desiredSize
     * @param method
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public HashtableClosedAddressImpl(int desiredSize,
                                      HashFunctionClosedAddressMethod method) {
        int realSize = desiredSize;

        if (method == HashFunctionClosedAddressMethod.DIVISION) {
            realSize = this.getPrimeAbove(desiredSize); // real size must the
            // the immediate prime
            // above
        }
            initiateInternalTable(realSize);
        HashFunction function = HashFunctionFactory.createHashFunction(method,
                realSize);
        this.hashFunction = function;
    }

    // AUXILIARY

    /**
     * It returns the prime number that is closest (and greater) to the given
     * number.
     * If the given number is prime, it is returned.
     * You can use the method Util.isPrime to check if a number is
     * prime.
     */
    int getPrimeAbove(int number) {
        while (!Util.isPrime(number)) {
            number++;
        }
        return number;
    }


    private LinkedList<T> getLinkedList(T element) {
        T[] a = (T[]) new LinkedList[10];
        LinkedList<T> result = null;
        int hashIndex = hash(element);
        if (hashIndex != -1) {
            if (table[hashIndex] == null) {
                table[hashIndex] = new LinkedList<T>();
                result = (LinkedList<T>) table[hashIndex];
            } else {
                result = ((LinkedList<T>) table[hashIndex]);
            }
        }
        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null && search(element) == null) {
            if (getLinkedList(element).isEmpty()) {

                getLinkedList(element).addFirst(element);

            } else {
                getLinkedList(element).addFirst(element);
                COLLISIONS++;
            }
            elements++;
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            if (myContains(element, getLinkedList(element))) {
                getLinkedList(element).remove(element);
                elements--;
            }
        }
    }

    @Override
    public T search(T element) {
        T returnAUx = null;
        if (element != null) {
            if (myContains(element, getLinkedList(element))) {
                returnAUx = element;
            }
        }
        return returnAUx;
    }

    @Override
    public int indexOf(T element) {
        Map mapa;
        mapa.contains
        int returnAux = -1;
        if (myContains(element, getLinkedList(element))) {
            returnAux = hash(element);
        }

        return returnAux;
    }

    private int hash(T element) {
        return ((HashFunctionClosedAddress) hashFunction).hash(element);
    }

    private boolean myContains(T element, LinkedList<T> list) {
        boolean returnAux = false;
        for (T elemento : list) {
            if (elemento.equals(element)) {
                returnAux = true;
            }
        }

        return returnAux;
    }

}
