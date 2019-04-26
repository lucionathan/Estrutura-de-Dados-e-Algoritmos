package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected DoubleLinkedListNode<T> last;

   public DoubleLinkedListImpl() {
      head = new DoubleLinkedListNode<T>();
      last = (DoubleLinkedListNode<T>) head;

   }

   @Override
   public void insert(T element) {
      if (element != null) {
         DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, null, null);
         newLast.setPrevious(getLast());
         newLast.setNext(new DoubleLinkedListNode<T>());
         getLast().setNext(newLast);
         if (getLast().isNIL()) {
            setHead(newLast);
         }
         setLast(newLast);
      }
   }

   @Override
   public void insertFirst(T element) {
      if (element != null) {
         DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, null, null);
         newHead.setNext(getHead());
         newHead.setPrevious(new DoubleLinkedListNode<T>());

         if (getHead() instanceof DoubleLinkedListNode) {
            ((DoubleLinkedListNode<T>) getHead()).setPrevious(newHead);
         }

         if (getHead().isNIL()) {
            setLast(newHead);
         }

         setHead(newHead);
      }
   }

   @Override
   public void removeFirst() {
      if (!getHead().isNIL()) {
         setHead(getHead().getNext());
         if (getHead().isNIL()) {
            setLast((DoubleLinkedListNode<T>) getHead());
         }
         if (getHead() instanceof DoubleLinkedListNode) {
            ((DoubleLinkedListNode<T>) getHead()).setPrevious(new DoubleLinkedListNode<T>());
         }
      }
   }

   @Override
   public void removeLast() {
      if (!getLast().isNIL()) {
         setLast(getLast().getPrevious());
         if (getLast().isNIL()) {
            setHead(getLast());
         }
         getLast().setNext(new DoubleLinkedListNode<T>(null, null, getLast()));
      }

   }

   public DoubleLinkedListNode<T> getLast() {
      return last;
   }

   public void setLast(DoubleLinkedListNode<T> last) {
      this.last = last;
   }

}
