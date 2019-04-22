package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
    }

    @Override
    public boolean isEmpty() {
        boolean returnVar = false;
        if (getHead().isNIL()) {
            returnVar = true;
        }

        return returnVar;
    }

    @Override
    public int size() {
        SingleLinkedListNode<T> auxHead = getHead();
        int size = 0;
        if (!getHead().isNIL()) {
            while (!auxHead.isNIL()) {
                size++;
                auxHead = auxHead.getNext();
            }
        }
        return size;
    }

    @Override
    public T search(T element) {
        T returnVar;
        SingleLinkedListNode<T> auxHead = getHead();

        if (element == null) {
            returnVar = null;

        } else {
            while (!auxHead.isNIL() && !auxHead.getData().equals(element)) {
                auxHead = auxHead.getNext();
            }

            returnVar = auxHead.getData();
        }
        return returnVar;
    }

    @Override
    public void insert(T element) {

        if (element != null) {
            SingleLinkedListNode<T> newHead;
            SingleLinkedListNode<T> auxHead = head;


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
                SingleLinkedListNode<T> auxHead = head;
                SingleLinkedListNode<T> previous = auxHead;

                while (!auxHead.getData().equals(element) && !auxHead.isNIL()) {
                    previous = auxHead;
                    auxHead = auxHead.getNext();

                }

                if (!auxHead.isNIL()) {
                    previous.setNext(auxHead.getNext());

                }
            }
        }
    }

    @Override
    public T[] toArray() {
        SingleLinkedListNode<T> auxHead = getHead();
        T[] array = (T[]) new Object[size()];
        int count = 0;

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
