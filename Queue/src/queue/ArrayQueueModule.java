package queue;

public class ArrayQueueModule {
    private static int size = 0;
    private static int head = 0;
    private static int tail = 0;
    private static Object[] arrayElements = new Object[8];

    //pre: element != NULL, head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length]
    public static void enqueue(Object element) {
        assert element != null;
        arrayExpand();
        size++;
        arrayElements[tail] = element;
        tail = (tail + 1) % arrayElements.length;
    }
    //post: size = size' + 1, ∀i, i ∈ [0...arrayElements.length - 1], arrayElements[i] != Null,
    //head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length]

    //pre: head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length], ∧
    // size == arrayElements.length
    private static void arrayExpand() {
        if (size < arrayElements.length) {
            return;
        }
        Object[] newElements = new Object[2 * size];
        int newElementsIndex = 0;
        if (head < tail) {
            for (int i = head; i < tail; i++, newElementsIndex++) {
                newElements[newElementsIndex] = arrayElements[i];
            }
        } else {
            for (int i = head; i < size; i++, newElementsIndex++) {
                newElements[newElementsIndex] = arrayElements[i];
            }
            for (int i = 0; i < head; i++, newElementsIndex++) {
                newElements[newElementsIndex] = arrayElements[i];
            }
        }
        arrayElements = newElements;
        head = 0;
        tail = size;
    }
    //post: size < arrayElements.length, head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]

    //pre: element != NULL, head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]
    public static void push(Object element) {
        arrayExpand();
        size++;
        head = (head - 1 + arrayElements.length) % arrayElements.length;
        arrayElements[head] = element;
    }
    //post: size = size' + 1, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]

    // pre: size > 0, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]
    public static Object remove() {
        assert size > 0;
        tail = (tail - 1 + arrayElements.length) % arrayElements.length;
        Object current = arrayElements[tail];
        arrayElements[tail] = null;
        size--;
        return current;
    }
    //post: R = arrayElements[tail - 1], ∧ size = size' - 1

    // pre: size > 0
    public static Object peek() {
        assert size > 0;
        return arrayElements[(tail - 1 + arrayElements.length) % arrayElements.length];
    }
    //post: R = arrayElements[tail - 1], ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    // pre: n > 0 -> head != tail, ∧ head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length], ∧
    // head ∈ [0...arrayElements.length - 1], ∧ tail ∈ [0...arrayElements.length]

    public static Object dequeue() {
        assert size > 0;
        Object current = arrayElements[head];
        arrayElements[head] = null;
        head = (head + 1) % (arrayElements.length);
        size--;
        return current;
    }
    // post:  size = size' - 1, ∧ R != Null, ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]
    // head ∈ [0...arrayElements.length - 1], tail ∈ [0...arrayElements.length], ∧ R = arrayElements[head']

    public static Object element() {
        assert size > 0;
        return arrayElements[head];
    }
    //post: R = arrayElements[head], ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static boolean isEmpty() {
        return size == 0;
    }
    //post: R = (size == 0), ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static int size() {
        return size;
    }
    //post: R = size, ∧ n = n', ∧ ∀i ∈ [0..arrayElements.length - 1] : arrayElements[i]' = arrayElements[i]

    public static void clear() {
        arrayElements = new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }
    //post: size = 0, ∧ head = 0, ∧ tail = 0, ∧ no arrayElements in queue
}
