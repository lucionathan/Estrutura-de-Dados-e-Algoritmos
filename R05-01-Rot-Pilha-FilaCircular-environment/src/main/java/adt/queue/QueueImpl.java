package adt.queue;

public class QueueImpl<T> implements Queue<T> {

    private T[] array;
    private int tail;

    public QueueImpl(int size) {
        array = (T[]) new Object[size];
        tail = -1;
    }

    @Override
    public T head() {
        return array[0];
    }

    @Override
    public boolean isEmpty() {
        if (tail == -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (tail == array.length - 1) {
            return true;
        }
        return false;
    }

    private void shiftLeft() {
        for (int i = 0; i <= tail; i++) {
            array[i] = array[i + 1];
        }
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        checkQueueOverflow();

        tail++;
        array[tail] = element;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        checkQueueUnderflow();

        T result = array[0];
        shiftLeft();
        tail--;

        return result;
    }

    private void checkQueueUnderflow() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
    }

    private void checkQueueOverflow() throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
    }

}
