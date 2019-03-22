import queue.LinkedQueue;

import java.util.Arrays;

public class ArrayQueueTest {
    private static void fill(LinkedQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Object currentArray[] = queue.toArray();
        System.out.println(Arrays.toString(currentArray));
    }

    private static void dump(LinkedQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue()
            );
        }
    }

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        fill(queue);
        dump(queue);
        fill(queue);
        queue.clear();
        dump(queue);
        queue.enqueue("Hello");
        queue.enqueue(47);
        dump(queue);
        fill(queue);

    }
}
