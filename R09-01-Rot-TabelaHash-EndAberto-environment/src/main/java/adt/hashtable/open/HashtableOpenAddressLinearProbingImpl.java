package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

    private final DELETED DEL = new DELETED();

    public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
        super(size);
        hashFunction = new HashFunctionLinearProbing<T>(size, method);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            int n = 0;
            while ((table[hash(element, n)] != null)
                    && !(table[hash(element, n)] instanceof DELETED && n < table.length && element.equals(hash(element, n)))) {
                COLLISIONS++;
                n++;
            }

            if (!isFull()) {
                this.table[hash(element, n)] = element;
                elements++;
            }
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            int n = 0;
            while ((table[hash(element, n)] != null) && !(table[hash(element, n)].equals(element)) && n < table.length) {
                n++;
            }

            if (table[hash(element, n)] != null) {
                if (element.equals(table[hash(element, n)])) {
                    table[hash(element, n)] = new DELETED();
                    elements--;
                }
            }
        }

    }

    @Override
    public T search(T element) {
        if (element != null) {
            int n = 0;
            while (table[hash(element, n)] != null && !(table[hash(element, n)].equals(element)) && n < table.length) {
                n++;
            }

            if (element.equals(table[hash(element, n)])) {
                return element;
            }
        }
        return null;
    }

    @Override
    public int indexOf(T element) {

        if (element != null) {
            int n = 0;
            while (table[hash(element, n)] != null && !(table[hash(element, n)].equals(element)) && n < table.length) {
                n++;
            }

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
}
