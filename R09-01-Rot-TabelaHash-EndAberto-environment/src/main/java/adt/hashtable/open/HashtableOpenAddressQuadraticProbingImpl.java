package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    private final DELETED DEL = new DELETED();

    public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                    HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            int n = insertAux(element);

            if (!isFull()) {
                this.table[hash(element, n)] = element;
                elements++;
            }
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            int n = getProbe(element);

            if (table[hash(element, n)] != null) {
                if (element.equals(table[hash(element, n)])) {
                    table[hash(element, n)] = DEL;
                    elements--;
                }
            }
        }

    }

    @Override
    public T search(T element) {
        if (element != null) {
            int n = getProbe(element);

            if (element.equals(table[hash(element, n)])) {
                return element;
            }
        }
        return null;
    }

    @Override
    public int indexOf(T element) {

        if (element != null) {
            int n = getProbe(element);

            if (element.equals(table[hash(element, n)])) {
                return hash(element, n);
            }
        }
        return -1;
    }

    private int hash(T element, int probe) {
        int hashIndex = -1;
        if (hashFunction instanceof HashFunctionOpenAddress) {
            hashIndex = ((HashFunctionOpenAddress) hashFunction).hash(element, probe);
        }
        return hashIndex;
    }

    private int getProbe(T element) {
        int n = 0;
        while (table[hash(element, n)] != null && !(table[hash(element, n)].equals(element)) && n < table.length) {
            n++;
        }
        return n;
    }

    private int insertAux(T element) {
        int n = 0;

        while (table[hash(element, n)] != null
                && !(table[hash(element, n)].equals(DEL) &&
                n < table.length &&
                element.equals(table[hash(element, n)])))
        {
            COLLISIONS++;
            n++;
        }
        return n;
    }

}
