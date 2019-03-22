import queue.ArrayQueueADT;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
        for (int i = 10; i < 15; i++) {
            ArrayQueueADT.push(queue, i);
        }
    }

    public static void dump(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.element(queue) + " " +
                            ArrayQueueADT.dequeue(queue)
            );
            System.out.println(
                    ArrayQueueADT.size(queue) + " " +
                            ArrayQueueADT.peek(queue) + " " +
                            ArrayQueueADT.remove(queue)
            );
        }
    }

    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        fill(queue);
        dump(queue);
        fill(queue);
        ArrayQueueADT.clear(queue);
        dump(queue);
        ArrayQueueADT.enqueue(queue, "Hello");
        ArrayQueueADT.enqueue(queue, 47);
        dump(queue);
    }
}
