package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void removeDuplicates() {
      SingleLinkedListNode<T> var0 = getHead();
      SingleLinkedListNode<T> var1 = var0;
      SingleLinkedListNode<T> var2;

      while (!var0.isNIL()) {
         var1 = var0;
         var2 = var1.getNext();
         T data = var1.getData();
         while (!var1.isNIL()) {
            if (data != null && data.equals(var2.getData())) {
               var2 = var2.getNext();
            } else {
               var1.setNext(var2);
               var1 = var2;
               var2 = var2.getNext();
            }
         }
         var0 = var0.getNext();
      }
   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

      SetLinkedListImpl newSet = new SetLinkedListImpl();

      SingleLinkedListNode<T> auxHead = getHead();

      while (!auxHead.getNext().isNIL()) {
         newSet.insert(auxHead.getData());
         auxHead = auxHead.getNext();
      }

      SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();

      while (!auxHead2.isNIL()) {
         newSet.insert(auxHead2.getData());
         auxHead2 = auxHead2.getNext();
      }
      newSet.removeDuplicates();
      return newSet;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SetLinkedListImpl newSet = new SetLinkedListImpl();

      SingleLinkedListNode<T> auxHead = getHead();
      SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();

      while (!auxHead.isNIL()) {
         T data = auxHead.getData();
         if (data != null) {
            if (otherSet.search(data) != null && otherSet.search(data).equals(data)) {
               newSet.insert(auxHead.getData());
            }
         }
         auxHead = auxHead.getNext();

      }
      newSet.removeDuplicates();
      return newSet;

   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      SingleLinkedListNode<T> auxHead = getHead();
      while (!auxHead.getNext().isNIL()) {
         auxHead = auxHead.getNext();
      }
      SingleLinkedListNode<T> auxHead2 = ((SingleLinkedListImpl<T>) otherSet).getHead();

      auxHead.setNext(auxHead2);
      removeDuplicates();
   }

}
