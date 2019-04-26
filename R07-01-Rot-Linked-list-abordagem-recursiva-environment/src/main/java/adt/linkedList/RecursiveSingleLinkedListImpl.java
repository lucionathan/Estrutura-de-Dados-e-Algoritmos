package adt.linkedList;

import java.util.Arrays;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

    protected T data;
    protected RecursiveSingleLinkedListImpl<T> next;

    public RecursiveSingleLinkedListImpl() {

    }

    public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
        this.data = data;
        this.next = next;
    }


    @Override
    public boolean isEmpty() {
        boolean returnAux;
        if (data == null) {
            returnAux = true;
        } else {
            returnAux = false;
        }

        return returnAux;
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        } else {
            return 1 + getNext().size();
        }
    }

    @Override
    public T search(T element) {

        if (isEmpty() || element == null) {

            return null;
        } else {
            if (data.equals(element)) {

                return element;
            } else {

                return getNext().search(element);
            }
        }

    }

    @Override
    public void insert(T element) {
        if (element != null) {

            if (isEmpty()) {
                setData(element);
                setNext(new RecursiveSingleLinkedListImpl<>());
            } else {
                getNext().insert(element);
            }
        }
    }

    @Override
    public void remove(T element) {
        if (!isEmpty() || element != null) {
            if (element.equals(getData())) {
                setData(getNext().getData());
                setNext(getNext().getNext());
            } else {
                getNext().remove(element);
            }
        }
    }

    @Override
    public T[] toArray() {

        T[] array = (T[]) new Object[size()];

        if (array.length > 0) {
            array[0] = getData();
            toArrayAux(array, getNext(), 1);
        }
        System.out.println(Arrays.toString(array));
        return array;

    }

    private void toArrayAux(T[] array, RecursiveSingleLinkedListImpl node, int count) {
        if (!node.isEmpty()) {

            array[count] = (T) node.getData();
            toArrayAux(array, node.getNext(), count + 1);
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RecursiveSingleLinkedListImpl<T> getNext() {
        return next;
    }

    public void setNext(RecursiveSingleLinkedListImpl<T> next) {
        this.next = next;
    }

}

