package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

    protected DoubleLinkedList<T> top;
    protected int size;

    public StackDoubleLinkedListImpl(int size) {
        this.size = size;
        this.top = new DoubleLinkedListImpl<T>();
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException();
        }

        if (element != null) {
            top.insert(element);
        }

    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException();
        }

        T removed = ((DoubleLinkedListImpl<T>) top).getLast().getData();
        top.removeLast();
        return removed;
    }

    @Override
    public T top() {
        T returnAux;
        if (isEmpty()) {
            returnAux = null;
        } else {
            returnAux = ((DoubleLinkedListImpl<T>) top).getLast().getData();
        }

        return returnAux;
    }

    @Override
    public boolean isEmpty() {
        return (top.size() == 0);
    }

    @Override
    public boolean isFull() {
        return (top.size() == size);
    }

}
