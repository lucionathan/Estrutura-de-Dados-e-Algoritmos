package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
    }

    private static final SingleLinkedListNode NIL = new SingleLinkedListNode();

    @Override
    public boolean isEmpty() {
        return head.isNIL();
    }

    @Override
    public int size() {
        SingleLinkedListNode<T> auxHead = getHead();
        int count = 0;
        if (!isEmpty()) {
            while (!auxHead.isNIL()) {
                auxHead = auxHead.getNext();
                count++;
            }
        }

        return count;
    }

    @Override
    public T search(T element) {
        T auxReturn = null;

        if (element != null) {
            SingleLinkedListNode<T> auxHead = getHead();
            while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
               auxHead = auxHead.getNext();
            }

            if (!auxHead.isNIL()) {
                auxReturn = auxHead.getData();
            }

        }
        return auxReturn;
    }

    @Override
    public void insert(T element) {
        if (element != null) {

            SingleLinkedListNode<T> newHead;
            SingleLinkedListNode<T> auxHead = getHead();

            if (auxHead.isNIL()) {
                newHead = new SingleLinkedListNode<T>(element, auxHead);
                setHead(newHead);
            } else {
                while (!auxHead.getNext().isNIL()) {
                    auxHead = auxHead.getNext();
                }

                newHead = new SingleLinkedListNode<T>(element, auxHead.getNext());
                auxHead.setNext(newHead);
            }
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            if (getHead().getData().equals(element)) {
                setHead(getHead().getNext());
            } else {

                SingleLinkedListNode<T> auxHead = getHead();
                while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
                    auxHead = auxHead.getNext();
                }

                if (!auxHead.isNIL()) {
                    auxHead.setData(auxHead.getNext().getData());
                    auxHead.setNext(auxHead.getNext().getNext());
                }
            }
        }
    }

    @Override
    public T[] toArray() {
        SingleLinkedListNode<T> auxHead = getHead();
        int count = 0;
        T[] array = (T[]) new Object[size()];

        while (count != size()) {
            array[count] = auxHead.getData();
            auxHead = auxHead.getNext();
            count++;
        }
        return array;
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head) {
        this.head = head;
    }

}
