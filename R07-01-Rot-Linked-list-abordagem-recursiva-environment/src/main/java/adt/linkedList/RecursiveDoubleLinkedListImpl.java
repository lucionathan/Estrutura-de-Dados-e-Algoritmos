package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
        RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

    protected RecursiveDoubleLinkedListImpl<T> previous;

    public RecursiveDoubleLinkedListImpl() {

    }

    public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
                                         RecursiveDoubleLinkedListImpl<T> previous) {
        super(data, next);
        this.previous = previous;

    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (isEmpty()) {
                setData(element);
                setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));

                if (getPrevious() == null) {
                    setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));
                }

            } else {
                getNext().insert(element);
            }
        }
    }

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            if (isEmpty()) {
                setData(element);
                setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));
                setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));

            } else {
                RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<T>();
                aux.setData(getData());
                aux.setNext(getNext());
                aux.setPrevious(this);
                setData(element);
                setNext(aux);
            }
        }
    }

    @Override
    public void removeFirst() {
        if (!isEmpty()) {
            setData(getNext().getData());
            setNext(getNext().getNext());
        }
    }

    @Override
    public void removeLast() {
        if (getNext().isEmpty()) {
            getPrevious().setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, getPrevious()));

        } else {
            ((RecursiveDoubleLinkedListImpl) getNext()).removeLast();
        }

    }

    public RecursiveDoubleLinkedListImpl<T> getPrevious() {
        return previous;
    }

    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
        this.previous = previous;
    }

}
