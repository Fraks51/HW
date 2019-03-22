package queue;

import java.util.Arrays;

public class ArrayQueue extends AbstractQueue implements Copiable {
    private int head = 0;
    private int tail = 0;
    private Object[] elements = new Object[8];

    protected void enqueueImpl(Object element) {
        arrayExpand();
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    private void arrayExpand() {
        if (size < elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * size];
        int newElementsIndex = 0;
        if (head < tail) {
            for (int i = head; i < tail; i++, newElementsIndex++) {
                newElements[newElementsIndex] = elements[i];
            }
        } else {
            for (int i = head; i < size; i++, newElementsIndex++) {
                newElements[newElementsIndex] = elements[i];
            }
            for (int i = 0; i < head; i++, newElementsIndex++) {
                newElements[newElementsIndex] = elements[i];
            }
        }
        elements = newElements;
        head = 0;
        tail = size;
    }

    protected void dequeueImpl() {
        head = (head + 1) % (elements.length);
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected void clearImpl() {
        elements = new Object[8];
        head = 0;
        tail = 0;
    }

    public ArrayQueue makeCopy() {
        final ArrayQueue copy = new ArrayQueue();
        copy.head = head;
        copy.tail = tail;
        copy.size = size;
        copy.elements = Arrays.copyOf(elements, elements.length);
        return copy;
    }
}
