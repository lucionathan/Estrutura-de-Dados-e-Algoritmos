package adt.queue;

public class CircularQueue<T> implements Queue<T> {

    private T[] array;
    private int tail;
    private int head;
    private int elements;

    public CircularQueue(int size) {
        array = (T[]) new Object[size];
        head = 0;
        tail = -1;
        elements = 0;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {

        checkQueueOverflow();

        if (tail == array.length && !isFull()) {
            tail = 0;
            array[tail] = element;

        } else {
            tail++;
            array[tail] = element;
            elements++;
        }
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        T result = null;

        checkQueueUnderflow();

        if (head == array.length && !isFull()) {
            result = array[head - 1];

            head = 0;

        } else if (head != -1) {

            result = array[head];
            head++;
            elements--;
        }

        return result;
    }

    @Override
    public T head() {
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if (elements <= 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isFull() {
        boolean result = false;

        if (elements == array.length) {
            result = true;
        }
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
