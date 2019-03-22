package queue;

public class ArrayQueueADT {
    private int size = 0;
    private int head = 0;
    private int tail = 0;
    private Object[] arrayElements = new Object[8];

    //pre: element != NULL, head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length]
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        arrayExpand(queue);
        queue.size++;
        queue.arrayElements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.arrayElements.length;
    }
    //post: size = size' + 1, ∀i, i ∈ [0...arrayElements.length - 1], arrayElements[i] != Null,
    //head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length]

    //pre: head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length], ∧
    // size == arrayElements.length
    private static void arrayExpand(ArrayQueueADT queue) {
        if (queue.size < queue.arrayElements.length) {
            return;
        }
        Object[] newElements = new Object[2 * queue.size];
        int newElementsIndex = 0;
        if (queue.head < queue.tail) {
            for (int i = queue.head; i < queue.tail; i++, newElementsIndex++) {
                newElements[newElementsIndex] = queue.arrayElements[i];
            }
        } else {
            for (int i = queue.head; i < queue.size; i++, newElementsIndex++) {
                newElements[newElementsIndex] = queue.arrayElements[i];
            }
            for (int i = 0; i < queue.head; i++, newElementsIndex++) {
                newElements[newElementsIndex] = queue.arrayElements[i];
            }
        }
        queue.arrayElements = newElements;
        queue.head = 0;
        queue.tail = queue.size;
    }
    //post: size < arrayElements.length, head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]

    //pre: element != NULL, head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]
    public static void push(ArrayQueueADT queue, Object element) {
        arrayExpand(queue);
        queue.size++;
        queue.head = (queue.head - 1 + queue.arrayElements.length) % queue.arrayElements.length;
        queue.arrayElements[queue.head] = element;
    }
    //post: size = size' + 1, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]

    // pre: size > 0, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.tail = (queue.tail - 1 + queue.arrayElements.length) % queue.arrayElements.length;
        Object current = queue.arrayElements[queue.tail];
        queue.arrayElements[queue.tail] = null;
        queue.size--;
        return current;
    }
    //post: R = arrayElements[tail - 1], ∧ size = size' - 1

    // pre: size > 0
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.arrayElements[(queue.tail - 1 + queue.arrayElements.length) % queue.arrayElements.length];
    }
    //post: R = arrayElements[tail - 1], ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    // pre: n > 0 -> head != tail, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length], ∧
    // head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        Object current = queue.arrayElements[queue.head];
        queue.arrayElements[queue.head] = null;
        queue.head = (queue.head + 1) % (queue.arrayElements.length);
        queue.size--;
        return current;
    }
    // post:  size = size' - 1, ∧ R != Null, ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]
    // head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length], ∧ R = arrayElements[head']

    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.arrayElements[queue.head];
    }
    //post: R = arrayElements[head], ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    //post: R = (size == 0), ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    //post: R = size, ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static void clear(ArrayQueueADT queue) {
        queue.arrayElements = new Object[8];
        queue.size = 0;
        queue.head = 0;
        queue.tail = 0;
    }
    //post: size = 0, ∧ head = 0, ∧ tail = 0, ∧ no arrayElements in queue
}
