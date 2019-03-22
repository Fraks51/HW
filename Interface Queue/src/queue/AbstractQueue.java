package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        Object currentObject = element();
        dequeueImpl();
        size--;
        return currentObject;
    }

    protected abstract void dequeueImpl();

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    public Object[] toArray() {
        Queue currentQueue = makeCopy();
        Object currentArray[] = new Object[currentQueue.size()];
        for (int i = 0; !currentQueue.isEmpty(); i++) {
            currentArray[i] = currentQueue.dequeue();
        }
        return currentArray;
    }

    public Queue filter(Predicate<Object> predicate) {
        Queue currentQueue = makeCopy();
        Object currentObject;
        for (int i = 0; i < size; i++) {
            currentObject = currentQueue.dequeue();
            if (predicate.test(currentObject)) {
                currentQueue.enqueue(currentObject);
            }
        }
        return currentQueue;
    }

    public Queue map(Function<Object, Object> function) {
        Queue currentQueue = makeCopy();
        Object currentObject;
        for (int i = 0; i < size; i++) {
            currentObject = currentQueue.dequeue();
            currentQueue.enqueue(function.apply(currentObject));
        }
        return currentQueue;
    }
}
