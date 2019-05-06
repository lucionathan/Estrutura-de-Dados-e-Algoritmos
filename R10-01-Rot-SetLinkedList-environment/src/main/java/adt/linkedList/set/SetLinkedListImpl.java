package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

    @Override
    public void removeDuplicates() {
        SingleLinkedListNode<T> auxHead = getHead();
        SingleLinkedListNode<T> var = getHead().getNext();
        while (!auxHead.isNIL()) {
            while (!var.isNIL()) {
                if (var.getData().equals(auxHead.getData())) {
                    remove(var.getData());
                }
                var.getNext();
            }
            auxHead.getNext();
        }
    }

    @Override
    public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

        SetLinkedListImpl newSet = new SetLinkedListImpl();

        SingleLinkedListNode<T> auxHead = getHead();


        while (!auxHead.isNIL()) {
            newSet.insert(auxHead.getData());
            auxHead.getNext();
        }

        SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();


        while (!auxHead2.isNIL()) {
            newSet.insert(auxHead2.getData());
            auxHead2.getNext();
        }

        return newSet;
    }

    @Override
    public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
        SetLinkedListImpl newSet = new SetLinkedListImpl();

        SingleLinkedListNode<T> auxHead = getHead();
        SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();


        while (!auxHead.isNIL()) {
            if (otherSet.search(auxHead.getData()).equals(auxHead.getData())) {
                newSet.insert(auxHead.getData());
                auxHead.getNext();
            }
        }
        return newSet;

    }

    @Override
    public void concatenate(SetLinkedList<T> otherSet) {
        SingleLinkedListNode<T> auxHead = getHead();
        while (!auxHead.isNIL()) {
            auxHead.getNext();
        }
        SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();

        auxHead.setNext(auxHead2);

        removeDuplicates();
    }

}
